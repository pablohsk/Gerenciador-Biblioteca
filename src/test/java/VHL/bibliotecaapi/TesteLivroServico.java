package VHL.bibliotecaapi;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import VHL.bibliotecaapi.repositorio.LivroRepositorio;
import VHL.bibliotecaapi.modelos.Livro;
import VHL.bibliotecaapi.servico.LivroServico;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class TesteLivroServico {

    @MockBean
    private LivroRepositorio livroRepositorio;

    @Test
    public void testListarTodosLivros() {
        Livro livro1 = new Livro();
        Livro livro2 = new Livro();
        when(livroRepositorio.findAll()).thenReturn(Arrays.asList(livro1, livro2));

        LivroServico livroServico = new LivroServico(livroRepositorio);
        List<Livro> livros = livroServico.listarTodosLivros();

        assertEquals(2, livros.size());
    }

    @Test
    public void testBuscarLivroPorId() {
        Long livroId = 1L;
        Livro livro = new Livro();
        livro.setId(livroId);
        when(livroRepositorio.findById(livroId)).thenReturn(Optional.of(livro));

        LivroServico livroServico = new LivroServico(livroRepositorio);
        Optional<Livro> resultado = livroServico.buscarLivroPorId(livroId);

        assertTrue(resultado.isPresent());
        assertEquals(livroId, resultado.get().getId());
    }

    @Test
    public void testBuscarLivrosPorTitulo() {
        String titulo = "Harry Potter";
        Livro livro1 = new Livro();
        Livro livro2 = new Livro();
        when(livroRepositorio.findByTitulo(titulo)).thenReturn(Arrays.asList(livro1, livro2));

        LivroServico livroServico = new LivroServico(livroRepositorio);
        List<Livro> livros = livroServico.buscarLivrosPorTitulo(titulo);

        assertEquals(2, livros.size());
    }

    @Test
    public void testBuscarLivrosPorAutor() {
        String autor = "J.K. Rowling";
        Livro livro1 = new Livro();
        Livro livro2 = new Livro();
        when(livroRepositorio.findByAutor(autor)).thenReturn(Arrays.asList(livro1, livro2));

        LivroServico livroServico = new LivroServico(livroRepositorio);
        List<Livro> livros = livroServico.buscarLivrosPorAutor(autor);

        assertEquals(2, livros.size());
    }

    @Test
    public void testAdicionarLivro() {
        Livro livro = new Livro();
        when(livroRepositorio.save(livro)).thenReturn(livro);

        LivroServico livroServico = new LivroServico(livroRepositorio);
        Livro resultado = livroServico.adicionarLivro(livro);

        assertNotNull(resultado);
    }

    @Test
    public void testAtualizarLivro() {
        Livro livro = new Livro();
        when(livroRepositorio.save(livro)).thenReturn(livro);

        LivroServico livroServico = new LivroServico(livroRepositorio);
        Livro resultado = livroServico.atualizarLivro(livro);

        assertNotNull(resultado);
    }

    @Test
    public void testExcluirLivro() {
        Long livroId = 1L;
        doNothing().when(livroRepositorio).deleteById(livroId);

        LivroServico livroServico = new LivroServico(livroRepositorio);
        assertDoesNotThrow(() -> livroServico.excluirLivro(livroId));
    }
}
