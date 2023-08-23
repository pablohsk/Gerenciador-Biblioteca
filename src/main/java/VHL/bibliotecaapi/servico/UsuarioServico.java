package VHL.bibliotecaapi.servico;

import org.springframework.stereotype.Service;
import VHL.bibliotecaapi.modelos.Usuario;
import VHL.bibliotecaapi.repositorio.UsuarioRepositorio;
import org.springframework.http.HttpStatus; 
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class UsuarioServico {

    private final UsuarioRepositorio usuarioRepositorio; // Injeta o repositório

    public UsuarioServico(UsuarioRepositorio usuarioRepositorio) {

        this.usuarioRepositorio = usuarioRepositorio;
    }

    // Busca usuários por nome
    public List<Usuario> buscarUsuariosPorNome(String nome) {

        return usuarioRepositorio.findByNome(nome);
    }

    // Busca usuários por email
    public List<Usuario> buscarUsuariosPorEmail(String email) {

        return usuarioRepositorio.findByEmail(email);
    }

    // Adiciona um novo usuário
    public Usuario adicionarUsuario(Usuario usuario) {

        return usuarioRepositorio.save(usuario);
    }

    // Atualiza informações de um usuário pelo ID
    public Usuario atualizarUsuario(Usuario usuario, Long id) {

        if (usuarioRepositorio.existsById(id)) {
            usuario.setId(id);
            return usuarioRepositorio.save(usuario);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
    }

    // Exclui um usuário pelo ID
    public void excluirUsuario(Long id) {
        usuarioRepositorio.deleteById(id);
    }
}
