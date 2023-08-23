package VHL.bibliotecaapi.controlador;

import VHL.bibliotecaapi.modelos.EmprestimoDTO;
import VHL.bibliotecaapi.modelos.Usuario;
import org.springframework.web.bind.annotation.*;
import VHL.bibliotecaapi.servico.EmprestimoServico;
import VHL.bibliotecaapi.modelos.Emprestimo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoControlador {

    private final EmprestimoServico emprestimoServico;
    private final Logger logger = LoggerFactory.getLogger(EmprestimoControlador.class);

    public EmprestimoControlador(EmprestimoServico emprestimoServico) {
        this.emprestimoServico = emprestimoServico;
    }

    @PostMapping
    public EmprestimoDTO realizarEmprestimo(@RequestBody Emprestimo emprestimo) {

        logger.info("Requisição para realizar um empréstimo por : {}", emprestimo.getUsuario().getNome());
        Emprestimo emprestimoRealizado = emprestimoServico.realizarEmprestimo(emprestimo);
        return new EmprestimoDTO(emprestimoRealizado);
    }

    @PutMapping("/{id}")
    public EmprestimoDTO atualizarEmprestimo(@RequestBody Emprestimo emprestimoAtualizado, @PathVariable Long id) {

        logger.info("Requisição para atualizar um empréstimo com ID {}: {}", id, emprestimoAtualizado);
        Emprestimo emprestimo = emprestimoServico.atualizaEmprestimo(emprestimoAtualizado, id);
        return new EmprestimoDTO(emprestimo);
    }

    @DeleteMapping("/{id}")
    public void devolverLivro(@PathVariable Long id) {

        logger.info("Requisição para devolver um livro com ID: {}", id);
        emprestimoServico.devolverLivro(id);
    }
}