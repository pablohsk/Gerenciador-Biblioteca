package VHL.bibliotecaapi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import VHL.bibliotecaapi.modelos.Emprestimo;

public interface EmprestimoRepositorio extends JpaRepository<Emprestimo, Long> {
}

