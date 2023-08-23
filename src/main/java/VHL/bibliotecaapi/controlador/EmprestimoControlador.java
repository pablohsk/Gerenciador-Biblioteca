package VHL.bibliotecaapi.controlador;

import VHL.bibliotecaapi.modelos.EmprestimoDTO;
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
    public EmprestimoDTO realizarEmprestimo(@RequestBody Emprestimo emprestimo) {
        Emprestimo emprestimoRealizado = emprestimoServico.realizarEmprestimo(emprestimo);
        return new EmprestimoDTO(emprestimoRealizado);
    }

    @PutMapping("/{id}")
    public EmprestimoDTO atualizarEmprestimo(@RequestBody Emprestimo emprestimoAtualizado, @PathVariable Long id) {
        Emprestimo emprestimo = emprestimoServico.atualizarEmprestimo(emprestimoAtualizado, id);
        return new EmprestimoDTO(emprestimo);
    }

    @DeleteMapping("/{id}")
    public void devolverLivro(@PathVariable Long id) {

        emprestimoServico.devolverLivro(id);
    }
}