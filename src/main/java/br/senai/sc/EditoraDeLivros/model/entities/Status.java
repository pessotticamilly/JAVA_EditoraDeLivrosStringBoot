package br.senai.sc.EditoraDeLivros.model.entities;

public enum Status {
    AGUARDANDO_REVISAO(),
    EM_REVISAO(),
    APROVADO(),
    AGUARDANDO_EDICAO(),
    REPROVADO(),
    PUBLICADO();
}