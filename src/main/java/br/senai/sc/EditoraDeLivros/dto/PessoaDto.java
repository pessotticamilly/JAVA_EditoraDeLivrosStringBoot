package br.senai.sc.EditoraDeLivros.dto;

import br.senai.sc.EditoraDeLivros.model.entities.Genero;

import javax.validation.constraints.NotBlank;

public class PessoaDto {
//    @NotBlank
    private Long cpf;
//    @NotBlank
    private String nome;
//    @NotBlank
    private String sobrenome;
//    @NotBlank
    private String email;
//    @NotBlank
    private String senha;
//    @NotBlank
    private Genero genero;

    public Long getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Genero getGenero() {
        return genero;
    }
}