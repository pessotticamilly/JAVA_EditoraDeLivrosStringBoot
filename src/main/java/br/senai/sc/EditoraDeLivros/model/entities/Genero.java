package br.senai.sc.EditoraDeLivros.model.entities;

import lombok.*;

@AllArgsConstructor
@Getter
public enum Genero {
    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    OUTRO("Outro");
    String nome;
}