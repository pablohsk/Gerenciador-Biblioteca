package VHL.bibliotecaapi.servico;

import VHL.bibliotecaapi.modelos.Usuario;
import org.springframework.stereotype.Service;
import VHL.bibliotecaapi.modelos.Emprestimo;
import VHL.bibliotecaapi.repositorio.EmprestimoRepositorio;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
public class EmprestimoServico {

    private final EmprestimoRepositorio emprestimoRepositorio;

    public EmprestimoServico(EmprestimoRepositorio emprestimoRepositorio) {

        this.emprestimoRepositorio = emprestimoRepositorio;
    }

    public Emprestimo realizarEmprestimo(Emprestimo emprestimo) {

        Usuario usuario = emprestimo.getUsuario();
//        if (usuario.getEmprestimo().size() >= 2) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já possui 2 empréstimos ativos.");
//        }

        // Defina outras verificações, como se o livro está disponível, etc.

        // Realizar o empréstimo
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucao(emprestimo.getDataEmprestimo().plusDays(7)); // Exemplo: 7 dias de empréstimo
        return emprestimoRepositorio.save(emprestimo);
    }

    public Emprestimo atualizarEmprestimo(Emprestimo emprestimoAtualizado, Long id) {

        Emprestimo emprestimoExistente = emprestimoRepositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empréstimo não encontrado."));

        // Implemente a lógica para atualizar o empréstimo, se necessário
        // Por exemplo, você pode permitir a extensão da data de devolução

        return emprestimoRepositorio.save(emprestimoAtualizado);
    }

    public void devolverLivro(Long id) {

        Emprestimo emprestimo = emprestimoRepositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empréstimo não encontrado."));

        if (emprestimo.getDataDevolucao().isBefore(LocalDate.now())) {
            // Implemente a lógica para calcular multas ou outras ações se a devolução estiver atrasada
        }

        emprestimo.getLivro().setDisponivel(true); // Libera o livro para empréstimo

        emprestimoRepositorio.delete(emprestimo);
    }
}