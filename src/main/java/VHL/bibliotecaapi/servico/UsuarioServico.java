package VHL.bibliotecaapi.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import VHL.bibliotecaapi.modelos.Usuario;
import VHL.bibliotecaapi.repositorio.UsuarioRepositorio;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServico {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    // Retorna uma lista de todos os usuários
    public List<Usuario> listarTodosUsuarios() {

        return usuarioRepositorio.findAll();
    }

    // Busca um usuário pelo ID
    public Optional<Usuario> buscarUsuarioPorId(Long id) {

        return usuarioRepositorio.findById(id);
    }

    // Busca um usuário pelo nome
    public List<Usuario> buscarUsuariosPorNome(String nome) {

        return usuarioRepositorio.findByNome(nome);
    }

    // Adiciona um novo usuário
    public Usuario adicionarUsuario(Usuario usuario) {

        return usuarioRepositorio.save(usuario);
    }

    // Atualiza informações de um usuário
    public Usuario atualizarUsuario(Usuario usuario) {

        return usuarioRepositorio.save(usuario);
    }

    // Exclui um usuário pelo ID
    public void excluirUsuario(Long id) {

        usuarioRepositorio.deleteById(id);
    }
}
