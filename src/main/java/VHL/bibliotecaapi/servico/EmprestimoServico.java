package VHL.bibliotecaapi.servico;

import VHL.bibliotecaapi.modelos.*;
import VHL.bibliotecaapi.erros.BadRequestError;
import VHL.bibliotecaapi.repositorio.LivroRepositorio;
import VHL.bibliotecaapi.repositorio.UsuarioRepositorio;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import VHL.bibliotecaapi.repositorio.EmprestimoRepositorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

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

    public ResponseEntity<?> realizarEmprestimo(Emprestimo emprestimo) {
        Usuario usuario = emprestimo.getUsuario();
        Livro livro = emprestimo.getLivro();

        if (emprestimoRepositorio.getEmprestimosByUsuario_Id(usuario.getId()).size() >= 2) {
            throw new BadRequestError("Usuário já possui 2 empréstimos ativos e não consegue mais pegar nenhum livro emprestado.");
        }

        if (emprestimoRepositorio.getEmprestimosByLivro_Id(livro.getId()).size() >= 1) {
            throw new BadRequestError("O livro não está disponível para empréstimo.");
        }

        // Realizar o empréstimo
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucao(emprestimo.getDataEmprestimo().plusDays(7)); // Exemplo: 7 dias de empréstimo

        return constroiMensagemSucessoRetorno(constroiObjetoRetorno(emprestimoRepositorio.save(emprestimo)), "O livro foi emprestado com sucesso!", HttpStatus.OK);
    }

    private Emprestimo constroiObjetoRetorno (Emprestimo obj){
        Emprestimo emprestimoRetorno = new Emprestimo();
        emprestimoRetorno = obj;
        Livro livroRetorno = livroRepositorio.findLivroById(obj.getLivro().getId());
        emprestimoRetorno.setLivro(livroRetorno);
        return emprestimoRetorno;
    }

    private ResponseEntity<?> constroiMensagemSucessoRetorno(Emprestimo object, String mensagemSucesso, HttpStatus httpStatus){
        MensagemSucessoDTO mensagemSucessoDTO = new MensagemSucessoDTO(mensagemSucesso, httpStatus, object);
        return ResponseEntity.status(httpStatus).contentType(MediaType.APPLICATION_JSON).body(mensagemSucessoDTO);
    }

    public List<EmprestimoDTO> listarTodosEmprestimos() {
        List<Emprestimo> emprestimos = emprestimoRepositorio.findAll();
        return emprestimos.stream()
                .map(EmprestimoDTO::new) // Supondo que você tenha um construtor em EmprestimoDTO que aceita Emprestimo
                .collect(Collectors.toList());
    }

    public Emprestimo atualizarEmprestimo(Emprestimo emprestimoAtualizado, Long id) {
        emprestimoRepositorio.findById(id)
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
