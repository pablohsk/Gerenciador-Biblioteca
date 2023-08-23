package VHL.bibliotecaapi.servico;

import org.springframework.stereotype.Service;
import VHL.bibliotecaapi.modelos.Usuario;
import VHL.bibliotecaapi.repositorio.UsuarioRepositorio;
import org.springframework.http.HttpStatus; 
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UsuarioServico {

    private final UsuarioRepositorio usuarioRepositorio; // Injeta o repositório
    private final Logger logger = LoggerFactory.getLogger(LivroServico.class);  // Criação da instância de logger para registrar mensagens de log na classe LivroServico

    public UsuarioServico(UsuarioRepositorio usuarioRepositorio) {

        this.usuarioRepositorio = usuarioRepositorio;
    }

    // Busca usuários por nome
    public List<Usuario> buscarUsuariosPorNome(String nome) {

        logger.info("Buscando usuarios por nome: {}", nome);
        return usuarioRepositorio.findByNome(nome);
    }

    // Busca usuários por email
    public List<Usuario> buscarUsuariosPorEmail(String email) {

        logger.info("Buscando livros por email: {}", email);
        return usuarioRepositorio.findByEmail(email);
    }

    // Adiciona um novo usuário
    public Usuario adicionarUsuario(Usuario usuario) {

        logger.info("Adicionando usuario: {}", usuario.getNome());
        return usuarioRepositorio.save(usuario);
    }

    // Atualiza informações de um usuário pelo ID
    public Usuario atualizarUsuario(Usuario usuario, Long id) {

        logger.info("Atualizando usuario de ID: {}", id);
        if (usuarioRepositorio.existsById(id)) {
            usuario.setId(id);
            return usuarioRepositorio.save(usuario);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
    }

    // Exclui um usuário pelo ID
    public void excluirUsuario(Long id) {

        logger.info("Excluindo usuario de ID: {}", id);
        usuarioRepositorio.deleteById(id);
    }
}
