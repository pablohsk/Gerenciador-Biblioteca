package VHL.bibliotecaapi.controlador;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable; 
import java.util.List;
import VHL.bibliotecaapi.servico.UsuarioServico;
import VHL.bibliotecaapi.modelos.Usuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {

    private final UsuarioServico usuarioServico; // Injeta o serviço

    public UsuarioControlador(UsuarioServico usuarioServico) {
        this.usuarioServico = usuarioServico;
    }

    @GetMapping("/porNome")
    public List<Usuario> getUsuariosPorNome(@RequestParam String nome) {
        return usuarioServico.buscarUsuariosPorNome(nome); // Usa o serviço para buscar usuario por nome
    }

    @GetMapping("/porEmail")
    public List<Usuario> getUsuariosPorEmail(@RequestParam String email) {
        return usuarioServico.buscarUsuariosPorEmail(email); // Usa o serviço para buscar usuário por email
    }
    
    @PostMapping
    public Usuario adicionarUsuario(@RequestBody Usuario usuario) {
        return usuarioServico.adicionarUsuario(usuario); // Usa o serviço para adicionar usuário
    }

    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@RequestBody Usuario usuarioAtualizado, @PathVariable Long id) {
        return usuarioServico.atualizarUsuario(usuarioAtualizado, id); // Usa o serviço para atualizar usuário
    }

    @DeleteMapping("/{id}")
    public void excluirUsuario(@PathVariable Long id) {
        usuarioServico.excluirUsuario(id); // Usa o serviço para excluir usuário
    }
    
}
