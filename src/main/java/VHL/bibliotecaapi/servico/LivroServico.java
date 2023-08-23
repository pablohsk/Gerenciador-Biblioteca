package VHL.bibliotecaapi.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import VHL.bibliotecaapi.modelos.Livro;
import VHL.bibliotecaapi.repositorio.LivroRepositorio;

import java.util.List;
import java.util.Optional;

@Service
public class LivroServico {

    @Autowired
    private LivroRepositorio livroRepositorio;

    public List<Livro> listarTodosLivros() {

        return livroRepositorio.findAll();
    }

    public Optional<Livro> buscarLivroPorId(Long id) {

        return livroRepositorio.findById(id);
    }

    public List<Livro> buscarLivrosPorTitulo(String titulo) {

        return livroRepositorio.findByTitulo(titulo);
    }

    public List<Livro> buscarLivrosPorAutor(String autor) {

        return livroRepositorio.findByAutor(autor);
    }

    public Livro adicionarLivro(Livro livro) {

        return livroRepositorio.save(livro);
    }

    public Livro atualizarLivro(Livro livro) {

        return livroRepositorio.save(livro);
    }

    public void excluirLivro(Long id) {

        livroRepositorio.deleteById(id);
    }
}