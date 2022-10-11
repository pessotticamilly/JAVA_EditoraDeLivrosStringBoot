package br.senai.sc.EditoraDeLivros.controller;

import br.senai.sc.EditoraDeLivros.model.service.PessoaService;

public class PessoaController {
    private PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

}
