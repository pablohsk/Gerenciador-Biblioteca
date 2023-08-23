//Declaração do pacote
package VHL.bibliotecaapi.modelos;

// Importações
import jakarta.persistence.*;

//Declaração de classe
@Entity
@Table(name = "TB_LIVRO")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "AUTOR")
    private String autor;
    @Column(name = "DISPONIVEL")
    private boolean disponivel;
    @OneToOne(mappedBy = "livro")
    private Emprestimo emprestimo;
    // Construtores, getters e setters

    // Construtor padrão (necessário para desserialização JSON)
    public Livro() {
    }

    // Outros construtores

    // Getters e setters

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getTitulo() {

        return titulo;
    }

    public void setTitulo(String titulo) {

        this.titulo = titulo;
    }

    public String getAutor() {

       return autor;
    }

    public void setAutor(String autor) {

        this.autor = autor;
    }

    public boolean isDisponivel() {

        return disponivel;
    }
    public void setDisponivel(boolean disponivel){

        this.disponivel = disponivel;
    }
}