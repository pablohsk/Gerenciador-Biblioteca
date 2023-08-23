package VHL.bibliotecaapi.controlador;

import VHL.bibliotecaapi.modelos.Usuario;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.http.HttpStatus; 
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import VHL.bibliotecaapi.repositorio.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @GetMapping("/porNome")
    public List<Usuario> getUsuariosPorNome(@RequestParam String nome) {
        return usuarioRepositorio.findByNome(nome);
    }

    @GetMapping("/porEmail")
    public List<Usuario> getUsuariosPorEmail(@RequestParam String email) {
        return usuarioRepositorio.findByEmail(email);
    }
    
    
    @PostMapping
    public Usuario adicionarUsuario(@RequestBody Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@RequestBody Usuario usuarioAtualizado, @PathVariable Long id) {
        if (usuarioRepositorio.existsById(id)) {
            usuarioAtualizado.setId(id);
            return usuarioRepositorio.save(usuarioAtualizado);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public void excluirUsuario(@PathVariable Long id) {
        usuarioRepositorio.deleteById(id);
    }

    // Outros métodos de consulta, se necessário
}