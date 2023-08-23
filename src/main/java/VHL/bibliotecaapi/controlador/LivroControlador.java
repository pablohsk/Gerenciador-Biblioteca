package VHL.bibliotecaapi.controlador;

import VHL.bibliotecaapi.modelos.Livro;
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
@RequestMapping("/livros")
public class LivroControlador {

    @Autowired
    private LivroRepositorio livroRepositorio;

    @GetMapping("/porTitulo")
    public List<Livro> getLivrosPorTitulo(@RequestParam String titulo) {
        return livroRepositorio.findByTitulo(titulo);
    }

    @GetMapping("/porAutor")
    public List<Livro> getLivrosPorAutor(@RequestParam String autor) {
        return livroRepositorio.findByAutor(autor);
    }

    @PostMapping
    public Livro adicionarLivro(@RequestBody Livro livro) {
        return livroRepositorio.save(livro);
    }

    @PutMapping("/{id}")
    public Livro atualizarLivro(@RequestBody Livro livroAtualizado, @PathVariable Long id) {
        if (livroRepositorio.existsById(id)) {
            livroAtualizado.setId(id);
            return livroRepositorio.save(livroAtualizado);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public void excluirLivro(@PathVariable Long id) {
        livroRepositorio.deleteById(id);
    }
}