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
public class Editora {
    @Id
    @Column(length = 14, nullable = false, unique = true)
    private Long cnpj;

    @Column(nullable = false)
    private String nome;

}