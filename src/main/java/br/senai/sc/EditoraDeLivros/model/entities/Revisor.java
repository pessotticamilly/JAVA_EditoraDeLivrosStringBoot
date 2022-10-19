package br.senai.sc.EditoraDeLivros.model.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_editora")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Revisor extends Pessoa {
    @Id
    @Column(length = 14, nullable = false, unique = true)
    private Long cpf;

    @Column(nullable = false)
    private String nome;
}