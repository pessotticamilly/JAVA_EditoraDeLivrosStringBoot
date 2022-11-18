package br.senai.sc.EditoraDeLivros.security;

import lombok.Data;
import lombok.NonNull;

@Data
public class TokenDto {
    @NonNull
    private String tipo;

    @NonNull
    private String token;
}
