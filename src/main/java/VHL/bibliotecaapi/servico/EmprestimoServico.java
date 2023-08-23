package VHL.bibliotecaapi.servico;

import VHL.bibliotecaapi.modelos.Livro;
import VHL.bibliotecaapi.erros.BadRequestError;
import VHL.bibliotecaapi.modelos.Usuario;
import VHL.bibliotecaapi.repositorio.LivroRepositorio;
import VHL.bibliotecaapi.repositorio.UsuarioRepositorio;
import org.springframework.stereotype.Service;
import VHL.bibliotecaapi.modelos.Emprestimo;
import VHL.bibliotecaapi.repositorio.EmprestimoRepositorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class EmprestimoServico {

    private final EmprestimoRepositorio emprestimoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final LivroRepositorio livroRepositorio;
    private final Logger logger = LoggerFactory.getLogger(EmprestimoServico.class);

    public EmprestimoServico(EmprestimoRepositorio emprestimoRepositorio, UsuarioRepositorio usuarioRepositorio, LivroRepositorio livroRepositorio) {
        this.emprestimoRepositorio = emprestimoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.livroRepositorio = livroRepositorio;
    }

    public void atualizaStatusLivro(Long id) {
        logger.info("Atualizando status do livro para indisponível, Livro ID: {}", id);
        Livro livroEmprestado = livroRepositorio.findLivroById(id);
        livroEmprestado.setDisponivel(false);
        livroRepositorio.save(livroEmprestado);
    }

    public Emprestimo realizarEmprestimo(Emprestimo emprestimo) {
        Usuario usuario = emprestimo.getUsuario();
        Livro livro = emprestimo.getLivro();

        if (emprestimoRepositorio.getEmprestimosByUsuario_Id(usuario.getId()).size() >= 2) {
            throw new BadRequestError("Usuário já possui 2 empréstimos ativos e não consegue mais pegar nenhum livro emprestado.");
        }

        if (emprestimoRepositorio.getEmprestimosByLivro_Id(livro.getId()).size() >= 1) {
            throw new BadRequestError("O livro não está disponível para empréstimo.");
        }

        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucao(emprestimo.getDataEmprestimo().plusDays(7)); // 7 dias de empréstimo
        atualizaStatusLivro(livro.getId());

        logger.info("Realizando empréstimo para o usuário de ID: {} do livro de ID {}",
                usuario.getId(), livro.getId());

        return emprestimoRepositorio.save(emprestimo);
    }

    public Emprestimo atualizaEmprestimo(Emprestimo emprestimoAtualizado, Long id) {
        Emprestimo emprestimoExistente = emprestimoRepositorio.findById(id)
                .orElseThrow(() -> new BadRequestError("Empréstimo não encontrado."));

        emprestimoAtualizado.setDataEmprestimo(LocalDate.now());
        emprestimoAtualizado.setDataDevolucao(emprestimoAtualizado.getDataEmprestimo().plusDays(7)); // 7 dias de empréstimo

        logger.info("Atualizando empréstimo de ID: {}. Nova data de devolução: {}", id, emprestimoAtualizado.getDataDevolucao());

        return emprestimoRepositorio.save(emprestimoAtualizado);
    }

    public void devolverLivro(Long id) {
        Emprestimo emprestimo = emprestimoRepositorio.findById(id)
                .orElseThrow(() -> new BadRequestError("Empréstimo não encontrado."));

        Livro livro = emprestimo.getLivro();
        logger.info("Devolvendo livro com ID de empréstimo: {}. Livro ID: {}", id, livro.getId());

        LocalDate dataDevolucaoPrevista = emprestimo.getDataDevolucao();
        LocalDate dataDevolucaoReal = LocalDate.now();

        if (dataDevolucaoReal.isAfter(dataDevolucaoPrevista)) {
            long diasAtraso = ChronoUnit.DAYS.between(dataDevolucaoPrevista, dataDevolucaoReal);
            double multaPorDia = 2.0; // Valor da multa por dia de atraso (exemplo)
            double multaTotal = diasAtraso * multaPorDia;
            String mensagem = String.format("Devolução com atraso de %d dias. Multa a ser paga: R$ %.2f", diasAtraso, multaTotal);
            logger.info(mensagem);
        }

        livro.setDisponivel(true); // Libera o livro para empréstimo
        emprestimoRepositorio.delete(emprestimo);
    }
}
