package br.senai.sc.EditoraDeLivros.security;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@Data
public class UsuarioDto {
    @NonNull
    private String email;

    @NonNull
    private String senha;
}
