package VHL.bibliotecaapi.controlador;

import VHL.bibliotecaapi.modelos.LivroDTO;
import VHL.bibliotecaapi.modelos.UsuarioDTO;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable; 
import java.util.List;
import java.util.stream.Collectors;

import VHL.bibliotecaapi.servico.UsuarioServico;
import VHL.bibliotecaapi.modelos.Usuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {

    private final UsuarioServico usuarioServico; // Injeta o serviço

    public UsuarioControlador(UsuarioServico usuarioServico) {
        this.usuarioServico = usuarioServico;
    }

    @GetMapping("/porNome/{nome}")
    public List<UsuarioDTO> getUsuariosPorNome(@PathVariable String nome) {
        List<Usuario> usuarios = usuarioServico.buscarUsuariosPorNome(nome);
        List<UsuarioDTO> usuarioDTOS = usuarios.stream().map(UsuarioDTO::new).collect(Collectors.toList());
        return usuarioDTOS;
    }

    @GetMapping("/porEmail/{email}")
    public List<UsuarioDTO> getUsuariosPorEmail(@PathVariable String email) {
        List<Usuario> usuarios = usuarioServico.buscarUsuariosPorEmail(email);
        List<UsuarioDTO> usuarioDTOS = usuarios.stream().map(UsuarioDTO::new).collect(Collectors.toList());
        return usuarioDTOS;
    }

    @PostMapping
    public UsuarioDTO adicionarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioAdicionado = usuarioServico.adicionarUsuario(usuario);
        return new UsuarioDTO(usuarioAdicionado);
    }

    @PutMapping("/{id}")
    public UsuarioDTO atualizarUsuario(@RequestBody Usuario usuarioAtualizado, @PathVariable Long id) {
        Usuario usuario = usuarioServico.atualizarUsuario(usuarioAtualizado, id);
        return new UsuarioDTO(usuario);
    }

    @DeleteMapping("/{id}")
    public void excluirUsuario(@PathVariable Long id) {
        usuarioServico.excluirUsuario(id); // Usa o serviço para excluir usuário
    }
    
}
