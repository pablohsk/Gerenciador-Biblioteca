package VHL.bibliotecaapi.controlador;

import VHL.bibliotecaapi.modelos.EmprestimoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import VHL.bibliotecaapi.servico.EmprestimoServico;
import VHL.bibliotecaapi.modelos.Emprestimo;

import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoControlador {

    private final EmprestimoServico emprestimoServico;

    public EmprestimoControlador(EmprestimoServico emprestimoServico) {
        this.emprestimoServico = emprestimoServico;
    }

    @PostMapping
    public ResponseEntity<?> realizarEmprestimo(@RequestBody Emprestimo emprestimo) {
        ResponseEntity<?> emprestimoRealizado = emprestimoServico.realizarEmprestimo(emprestimo);
        return emprestimoRealizado;
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

    @GetMapping("/listar")
    public List<EmprestimoDTO> listarTodosEmprestimos() {
        return emprestimoServico.listarTodosEmprestimos();
    }
}