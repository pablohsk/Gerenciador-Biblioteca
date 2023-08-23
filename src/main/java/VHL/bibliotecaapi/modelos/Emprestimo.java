//Declaração do pacote
package VHL.bibliotecaapi.modelos;

//Importações
import jakarta.persistence.*;
import java.time.LocalDate;

//Declaração de classe
@Entity
@Table(name = "TB_EMPRESTIMO")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @OneToOne
    @JoinColumn(name = "LIVRO_ID", referencedColumnName = "ID")
    private Livro livro; // Referência ao livro emprestado
    @ManyToOne
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "ID")
    private Usuario usuario; // Referência ao usuário que realizou o empréstimo
    @Column(name = "DT_EMPRESTIMO")
    private LocalDate dataEmprestimo; // Data em que o empréstimo foi feito
    @Column(name = "DT_DEVOLUCAO")
    private LocalDate dataDevolucao; // Data prevista para a devolução

    // Construtores
    public Emprestimo() {
    }

    public Emprestimo(Livro livro, Usuario usuario, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
