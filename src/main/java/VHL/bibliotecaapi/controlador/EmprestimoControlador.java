package VHL.bibliotecaapi.controlador;

import org.springframework.web.bind.annotation.*;
import VHL.bibliotecaapi.servico.EmprestimoServico;
import VHL.bibliotecaapi.modelos.Emprestimo;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoControlador {

    private final EmprestimoServico emprestimoServico;

    public EmprestimoControlador(EmprestimoServico emprestimoServico) {
        this.emprestimoServico = emprestimoServico;
    }

    @PostMapping
    public Emprestimo realizarEmprestimo(@RequestBody Emprestimo emprestimo) {
        return emprestimoServico.realizarEmprestimo(emprestimo);
    }

    @PutMapping("/{id}")
    public Emprestimo atualizarEmprestimo(@RequestBody Emprestimo emprestimoAtualizado, @PathVariable Long id) {
        return emprestimoServico.atualizarEmprestimo(emprestimoAtualizado, id);
    }

    @DeleteMapping("/{id}")
    public void devolverLivro(@PathVariable Long id) {
        emprestimoServico.devolverLivro(id);
    }
}