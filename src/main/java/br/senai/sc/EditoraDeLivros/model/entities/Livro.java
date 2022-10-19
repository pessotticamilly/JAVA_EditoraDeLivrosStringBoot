package br.senai.sc.EditoraDeLivros.model.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tb_livro")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
@EqualsAndHashCode
public class Livro {
    @Id
    @Column(length = 13, nullable = false, unique = true)
    private Long isbn;

    @Column(length = 50, nullable = false)
    private String titulo;

    @Column(nullable = false)
    private Integer qntdPaginas;

    @Column( nullable = false)
    private Double paginasRevisadas;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @JoinColumn(name = "cpf_revisor")
    @ManyToOne
    private Revisor revisor;

    @JoinColumn(name = "cpf_autor")
    @ManyToOne
    private Autor autor;

    @JoinColumn(name = "cnpj_editora")
    @ManyToOne
    private Editora editora;
}