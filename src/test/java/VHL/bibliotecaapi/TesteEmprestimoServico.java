package VHL.bibliotecaapi;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import VHL.bibliotecaapi.modelos.Emprestimo;
import VHL.bibliotecaapi.modelos.Livro;
import VHL.bibliotecaapi.modelos.Usuario;
import VHL.bibliotecaapi.repositorio.EmprestimoRepositorio;
import VHL.bibliotecaapi.repositorio.LivroRepositorio;
import VHL.bibliotecaapi.repositorio.UsuarioRepositorio;
import VHL.bibliotecaapi.servico.EmprestimoServico;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

@SpringBootTest
public class TesteEmprestimoServico {

    @Mock
    private EmprestimoRepositorio emprestimoRepositorio;

    @Mock
    private UsuarioRepositorio usuarioRepositorio;

    @Mock
    private LivroRepositorio livroRepositorio;

    @InjectMocks
    private EmprestimoServico emprestimoServico;

    @Test
    public void testAtualizarEmprestimo() {
        Long id = 1L;
        Emprestimo emprestimoAtualizado = new Emprestimo();
        emprestimoAtualizado.setId(id);

        Emprestimo emprestimoExistente = new Emprestimo();
        when(emprestimoRepositorio.findById(id)).thenReturn(Optional.of(emprestimoExistente));
        when(emprestimoRepositorio.save(emprestimoAtualizado)).thenReturn(emprestimoAtualizado);

        Emprestimo result = emprestimoServico.atualizarEmprestimo(emprestimoAtualizado, id);

        assertEquals(id, result.getId());
    }

    @Test
    public void testRealizarEmprestimo() {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuario(new Usuario());
        emprestimo.setLivro(new Livro());

        when(emprestimoRepositorio.getEmprestimosByUsuario_Id(anyLong())).thenReturn(Collections.emptyList());
        when(emprestimoRepositorio.getEmprestimosByLivro_Id(anyLong())).thenReturn(Collections.emptyList());
        when(emprestimoRepositorio.save(any(Emprestimo.class))).thenReturn(emprestimo);

        ResponseEntity<?> result = emprestimoServico.realizarEmprestimo(emprestimo);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}
