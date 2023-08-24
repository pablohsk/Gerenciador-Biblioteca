package VHL.bibliotecaapi.modelos;

import java.time.LocalDate;

public class EmprestimoDTO {
    private Long id;
    private LivroDTO livro;
    private UsuarioDTO usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public EmprestimoDTO(Emprestimo emprestimo) {
        this.id = emprestimo.getId();
        this.livro = new LivroDTO(emprestimo.getLivro());
        this.usuario = new UsuarioDTO(emprestimo.getUsuario());
        this.dataEmprestimo = emprestimo.getDataEmprestimo();
        this.dataDevolucao = emprestimo.getDataDevolucao();
    }

    // Getters e setters

    public Long getId() {

        return id;
    }

    public Long setId(long id) {

        return id;
    }

    public LivroDTO getLivro() {

        return livro;
    }

    public UsuarioDTO getUsuario() {

        return usuario;
    }

    public LocalDate getDataEmprestimo() {

        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {

        return dataDevolucao;
    }
}

