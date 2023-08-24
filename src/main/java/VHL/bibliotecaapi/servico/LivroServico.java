package VHL.bibliotecaapi.servico;

import org.springframework.stereotype.Service;
import VHL.bibliotecaapi.repositorio.LivroRepositorio;
import VHL.bibliotecaapi.modelos.Livro;
import java.util.List;
import java.util.Optional;

@Service
public class LivroServico {

    private final LivroRepositorio livroRepositorio; // Injeta o repositório

    public LivroServico(LivroRepositorio livroRepositorio) {
        this.livroRepositorio = livroRepositorio;
    }

    // Retorna uma lista de todos os livros
    public List<Livro> listarTodosLivros() {
        return livroRepositorio.findAll();
    }

    // Busca um livro pelo ID
    public Optional<Livro> buscarLivroPorId(Long id) {
        return livroRepositorio.findById(id);
    }

    // Busca livros por título
    public List<Livro> buscarLivrosPorTitulo(String titulo) {
        return livroRepositorio.findByTitulo(titulo);
    }

    // Busca livros por autor
    public List<Livro> buscarLivrosPorAutor(String autor) {
        return livroRepositorio.findByAutor(autor);
    }

    // Adiciona um novo livro
    public Livro adicionarLivro(Livro livro) {
        return livroRepositorio.save(livro);
    }

    // Atualiza informações de um livro
    public Livro atualizarLivro(Livro livro) {
        return livroRepositorio.save(livro);
    }

    // Exclui um livro pelo ID
    public void excluirLivro(Long id) {
        livroRepositorio.deleteById(id);
    }
}
