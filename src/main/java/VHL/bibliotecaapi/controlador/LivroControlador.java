package VHL.bibliotecaapi.controlador;

import VHL.bibliotecaapi.servico.LivroServico;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

import VHL.bibliotecaapi.modelos.*;

@RestController
@RequestMapping("/livros")
public class LivroControlador {


    private final LivroServico livroServico; // Injeta o serviço

    public LivroControlador(LivroServico livroServico) {

        this.livroServico = livroServico;
    }

    @GetMapping("/porTitulo/{titulo}")
    public List<Livro> getLivrosPorTitulo(@PathVariable String titulo) {

        return livroServico.buscarLivrosPorTitulo(titulo); // Usa o serviço para buscar livros por título
    }

    @GetMapping("/porAutor/{autor}")
    public List<Livro> getLivrosPorAutor(@PathVariable String autor) {

        return livroServico.buscarLivrosPorAutor(autor); // Usa o serviço para buscar livros por autor
    }

    @PostMapping
    public Livro adicionarLivro(@RequestBody Livro livro) {

        return livroServico.adicionarLivro(livro); // Usa o serviço para adicionar um livro
    }


    //lalala

    @PutMapping("/{id}")
    public Livro atualizarLivro(@RequestBody Livro livroAtualizado, @PathVariable Long id) {

        return livroServico.atualizarLivro(livroAtualizado); // Usa o serviço para atualizar um livro
    }

    @DeleteMapping("/{id}")
    public void excluirLivro(@PathVariable Long id) {

        livroServico.excluirLivro(id); // Usa o serviço para excluir um livro
    }
}
