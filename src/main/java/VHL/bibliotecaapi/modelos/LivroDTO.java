package VHL.bibliotecaapi.modelos;

public class LivroDTO {
    private Long id;
    private String titulo;
    private String autor;
    private boolean disponivel;

    // Construtores, getters e setters

    // Construtor a partir de um objeto Livro
    public LivroDTO(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.autor = livro.getAutor();
        this.disponivel = livro.isDisponivel();
    }

    // Getters e setters

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }
}