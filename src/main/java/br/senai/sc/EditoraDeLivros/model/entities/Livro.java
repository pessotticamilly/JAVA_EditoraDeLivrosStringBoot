package br.senai.sc.EditoraDeLivros.model.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "livro")
@AllArgsConstructor
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
    @Column(nullable = false)
    private Revisor revisor;
    @Column(nullable = false)
    private Autor autor;
    @Column
    private Editora editora;
}