package VHL.bibliotecaapi.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import VHL.bibliotecaapi.modelos.*;
import org.springframework.data.jpa.repository.Query;

//Declaração do pacote e importações necessárias

//Definição da interface LivroRepositorio que estende JpaRepository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    // Esta interface herda todos os métodos CRUD do JpaRepository:
    // - save(): Salva ou atualiza um usuário no banco de dados
    // - findById(): Busca um usuário pelo ID
    // - findAll(): Retorna todos os usuários no banco de dados
    // - deleteById(): Exclui um usuário pelo ID
	
    List<Usuario> findByNome(String nome);

    List<Usuario> findByEmail(String email);
    
    // Método personalizado para buscar usuários pelo livro emprestado
    // @Query("SELECT u FROM Usuario u JOIN u.livrosEmprestados l WHERE l = :livro")
    // List<Usuario> findByLivroEmprestado(Livro livro);

}