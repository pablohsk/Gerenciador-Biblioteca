package VHL.bibliotecaapi.modelos;

public class UsuarioDTO {
    private Long id;
    private String nome;
    private String email;

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
    }

    // Getters e setters

    public Long getId() {

        return id;
    }

    public String getNome() {

        return nome;
    }

    public String getEmail() {

        return email;
    }

}
