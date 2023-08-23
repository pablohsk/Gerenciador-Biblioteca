package VHL.bibliotecaapi.controlador;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import VHL.bibliotecaapi.servico.LivroServico;
import VHL.bibliotecaapi.modelos.*;

@RestController
@RequestMapping("/livros")
public class LivroControlador {


    private final LivroServico livroServico; // Injeta o serviço
    private final Logger logger = LoggerFactory.getLogger(LivroControlador.class);

    public LivroControlador(LivroServico livroServico) {
        this.livroServico = livroServico;
    }

    @GetMapping("/porTitulo/{titulo}")
    public List<LivroDTO> getLivrosPorTitulo(@PathVariable String titulo) {

        logger.info("Requisição para buscar livros por título: {}", titulo);
        List<Livro> livros = livroServico.buscarLivrosPorTitulo(titulo);
        List<LivroDTO> livrosDTO =  livros.stream().map(LivroDTO::new).collect(Collectors.toList()); // Usa o serviço para buscar livros por título
        return livrosDTO;
    }

    @GetMapping("/porAutor/{autor}")
    public List<LivroDTO> getLivrosPorAutor(@PathVariable String autor) {

        logger.info("Requisição para buscar livros por autor: {}", autor);
        List<Livro> livros = livroServico.buscarLivrosPorAutor(autor);
        List<LivroDTO> livrosDTO = livros.stream().map(LivroDTO::new).collect(Collectors.toList());
        return livrosDTO;
    }

    @PostMapping
    public LivroDTO adicionarLivro(@RequestBody Livro livro) {

        logger.info("Requisição para adicionar um novo livro: {}", livro.getTitulo());
        Livro livroAdicionado = livroServico.adicionarLivro(livro);
        return new LivroDTO(livroAdicionado);
    }

    @PutMapping("/{id}")
    public LivroDTO atualizarLivro(@RequestBody Livro livroAtualizado, @PathVariable Long id) {

        logger.info("Requisição para atualizar um novo livro: {}", livroAtualizado.getTitulo());
        Livro livro = livroServico.atualizarLivro(livroAtualizado);
        return new LivroDTO(livro);
    }

    @DeleteMapping("/{id}")
    public void excluirLivro(@PathVariable Long id) {

        logger.info("Requisição para excluir um livro: {}", id);
        livroServico.excluirLivro(id); // Usa o serviço para excluir um livro
    }
}
