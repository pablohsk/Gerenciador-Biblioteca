package VHL.bibliotecaapi.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import VHL.bibliotecaapi.modelos.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    List<Usuario> findByNome(String nome);

    List<Usuario> findByEmail(String email);

}