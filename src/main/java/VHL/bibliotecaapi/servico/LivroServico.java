package VHL.bibliotecaapi.servico;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import VHL.bibliotecaapi.repositorio.LivroRepositorio;
import VHL.bibliotecaapi.modelos.Livro;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LivroServico {

    private final LivroRepositorio livroRepositorio; // Injeta o repositório
    private final Logger logger = LoggerFactory.getLogger(LivroServico.class);  // Criação da instância de logger para registrar mensagens de log na classe LivroServico

    public LivroServico(LivroRepositorio livroRepositorio) {
        this.livroRepositorio = livroRepositorio;
    }

    // Retorna uma lista de todos os livros
    public List<Livro> listarTodosLivros() {

        logger.info("Buscando todos os livros");
        return livroRepositorio.findAll();
    }

    // Busca um livro pelo ID
    public Optional<Livro> buscarLivroPorId(Long id) {

        logger.info("Buscando livros por ID: {}", id);
        return livroRepositorio.findById(id);
    }

    // Busca livros por título
    public List<Livro> buscarLivrosPorTitulo(String titulo) {

        logger.info("Buscando livros por título: {}", titulo);
        return livroRepositorio.findByTitulo(titulo);
    }

    // Busca livros por autor
    public List<Livro> buscarLivrosPorAutor(String autor) {

        logger.info("Buscando livros por autor: {}", autor);
        return livroRepositorio.findByAutor(autor);
    }

    // Adiciona um novo livro
    public Livro adicionarLivro(Livro livro) {

        logger.info("Adicionando livro: {}",livro.getTitulo());
        return livroRepositorio.save(livro);
    }

    // Atualiza informações de um livro
    public Livro atualizarLivro(Livro livro) {

        logger.info("Adicionando livro: {}",livro.getTitulo());
        return livroRepositorio.save(livro);
    }

    // Exclui um livro pelo ID
    public void excluirLivro(Long id) {

        logger.info("Adicionando livro: {}",id);
        livroRepositorio.deleteById(id);
    }

}
