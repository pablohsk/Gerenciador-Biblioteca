package VHL.bibliotecaapi.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import VHL.bibliotecaapi.modelos.Livro;
import org.springframework.stereotype.Repository;

// Declaração do pacote e importações necessárias

// Definição da interface LivroRepositorio que estende JpaRepository
@Repository
public interface LivroRepositorio extends JpaRepository<Livro, Long> {
    // Esta interface herda todos os métodos CRUD do JpaRepository:
    // - save(): Salva ou atualiza um livro no banco de dados
    // - findById(): Busca um livro pelo ID
    // - findAll(): Retorna todos os livros no banco de dados
    // - deleteById(): Exclui um livro pelo ID
	
    List<Livro> findByAutor(String autor);

    List<Livro> findByTitulo(String titulo);

    List<Livro> findByDisponivelTrue();

    Livro findLivroById(Long id);
}