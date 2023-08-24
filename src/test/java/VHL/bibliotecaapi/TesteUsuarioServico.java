package VHL.bibliotecaapi;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import VHL.bibliotecaapi.repositorio.UsuarioRepositorio;
import VHL.bibliotecaapi.modelos.Usuario;
import VHL.bibliotecaapi.servico.UsuarioServico;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TesteUsuarioServico {

    @MockBean
    private UsuarioRepositorio usuarioRepositorio;

    @Test
    public void testBuscarUsuariosPorNome() {
        String nome = "João";
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        when(usuarioRepositorio.findByNome(nome)).thenReturn(Arrays.asList(usuario1, usuario2));

        UsuarioServico usuarioServico = new UsuarioServico(usuarioRepositorio);
        List<Usuario> usuarios = usuarioServico.buscarUsuariosPorNome(nome);

        assertEquals(2, usuarios.size());
    }

    @Test
    public void testBuscarUsuariosPorEmail() {
        String email = "joao@example.com";
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        when(usuarioRepositorio.findByEmail(email)).thenReturn(Arrays.asList(usuario1, usuario2));

        UsuarioServico usuarioServico = new UsuarioServico(usuarioRepositorio);
        List<Usuario> usuarios = usuarioServico.buscarUsuariosPorEmail(email);

        assertEquals(2, usuarios.size());
    }

    @Test
    public void testAdicionarUsuario() {
        Usuario usuario = new Usuario();
        when(usuarioRepositorio.save(usuario)).thenReturn(usuario);

        UsuarioServico usuarioServico = new UsuarioServico(usuarioRepositorio);
        Usuario resultado = usuarioServico.adicionarUsuario(usuario);

        assertNotNull(resultado);
    }

    @Test
    public void testAtualizarUsuario() {
        Long usuarioId = 1L;
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        when(usuarioRepositorio.existsById(usuarioId)).thenReturn(true);
        when(usuarioRepositorio.save(usuario)).thenReturn(usuario);

        UsuarioServico usuarioServico = new UsuarioServico(usuarioRepositorio);
        Usuario resultado = usuarioServico.atualizarUsuario(usuario, usuarioId);

        assertNotNull(resultado);
        assertEquals(usuarioId, resultado.getId());
    }

    @Test
    public void testAtualizarUsuarioNotFound() {
        Long usuarioId = 1L;
        Usuario usuario = new Usuario();
        when(usuarioRepositorio.existsById(usuarioId)).thenReturn(false);

        UsuarioServico usuarioServico = new UsuarioServico(usuarioRepositorio);
        assertThrows(ResponseStatusException.class, () -> usuarioServico.atualizarUsuario(usuario, usuarioId));
    }

    @Test
    public void testExcluirUsuario() {
        Long usuarioId = 1L;
        doNothing().when(usuarioRepositorio).deleteById(usuarioId);

        UsuarioServico usuarioServico = new UsuarioServico(usuarioRepositorio);
        assertDoesNotThrow(() -> usuarioServico.excluirUsuario(usuarioId));
    }
}
