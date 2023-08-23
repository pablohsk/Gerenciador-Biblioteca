package VHL.bibliotecaapi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import VHL.bibliotecaapi.modelos.Emprestimo;

import java.util.List;

public interface EmprestimoRepositorio extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> getEmprestimosByUsuario_Id(Long Id);
    List<Emprestimo> getEmprestimosByLivro_Id(Long Id);
}

