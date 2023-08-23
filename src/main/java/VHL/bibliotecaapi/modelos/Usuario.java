//Declaração do pacote
package VHL.bibliotecaapi.modelos;

//Importações
import jakarta.persistence.*;
import java.util.List;
import VHL.bibliotecaapi.modelos.Emprestimo;

//Declaração de Classe
@Entity
@Table(name = "TB_USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "EMAIL")
    private String email;
    @OneToOne(mappedBy = "usuario")
    private Emprestimo emprestimo;

    // Construtores
    public Usuario() {
    }

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    // Getters e Setters
    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public Emprestimo getEmprestimo() {

        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {

        this.emprestimo = emprestimo;
    }
}
