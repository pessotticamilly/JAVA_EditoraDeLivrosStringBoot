package br.senai.sc.EditoraDeLivros.model.entities;

import lombok.*;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@Table(name = "tb_pessoa")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
@EqualsAndHashCode
public class Pessoa {
    @Id
    @Column(length = 11, nullable = false, unique = true)
    private Long cpf;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(length = 50, nullable = false)
    private String sobrenome;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 18, nullable = false)
    private String senha;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Genero genero;
}