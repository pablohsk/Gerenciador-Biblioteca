package VHL.bibliotecaapi.servico;

import VHL.bibliotecaapi.modelos.Livro;
import VHL.bibliotecaapi.erros.BadRequestError;
import VHL.bibliotecaapi.repositorio.LivroRepositorio;
import VHL.bibliotecaapi.repositorio.UsuarioRepositorio;
import org.springframework.stereotype.Service;
import VHL.bibliotecaapi.modelos.Emprestimo;
import VHL.bibliotecaapi.repositorio.EmprestimoRepositorio;

import java.time.LocalDate;

@Service
public class EmprestimoServico {

    private final EmprestimoRepositorio emprestimoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final LivroRepositorio livroRepositorio;

    public EmprestimoServico(EmprestimoRepositorio emprestimoRepositorio, UsuarioRepositorio usuarioRepositorio, LivroRepositorio livroRepositorio) {
        this.emprestimoRepositorio = emprestimoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.livroRepositorio = livroRepositorio;
    }

    public void atualizaStatusLivro(Long id){

        Livro livroEmprestado = livroRepositorio.findLivroById(id);
        livroEmprestado.setDisponivel(false);
        livroRepositorio.save(livroEmprestado);
    }
    public Emprestimo realizarEmprestimo(Emprestimo emprestimo) {
        if (emprestimoRepositorio.getEmprestimosByUsuario_Id(emprestimo.getUsuario().getId()).size() >= 2) {
            throw new BadRequestError("Usuário já possui 2 empréstimos ativos e não consegue mais pegar nenhum livro emprestado.");
        }

        // Defina outras verificações, como se o livro está disponível, etc.
        if (emprestimoRepositorio.getEmprestimosByLivro_Id(emprestimo.getLivro().getId()).size() >= 1) {
            throw new BadRequestError("O livro não está disponível para empréstimo.");
        }

        // Realizar o empréstimo
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucao(emprestimo.getDataEmprestimo().plusDays(7)); // 7 dias de empréstimo
        atualizaStatusLivro(emprestimo.getLivro().getId());
        return emprestimoRepositorio.save(emprestimo);
    }

    public Emprestimo atualizarEmprestimo(Emprestimo emprestimoAtualizado, Long id) {
        Emprestimo emprestimoExistente = emprestimoRepositorio.findById(id)
                .orElseThrow(() -> new BadRequestError("Empréstimo não encontrado."));

        // Implemente a lógica para atualizar o empréstimo, se necessário
        // Por exemplo, você pode permitir a extensão da data de devolução

        return emprestimoRepositorio.save(emprestimoAtualizado);
    }

    public void devolverLivro(Long id) {
        Emprestimo emprestimo = emprestimoRepositorio.findById(id)
                .orElseThrow(() -> new BadRequestError("Empréstimo não encontrado."));

        if (emprestimo.getDataDevolucao().isBefore(LocalDate.now())) {
            // Implemente a lógica para calcular multas ou outras ações se a devolução estiver atrasada
        }

        emprestimo.getLivro().setDisponivel(true); // Libera o livro para empréstimo

        emprestimoRepositorio.delete(emprestimo);
    }
}