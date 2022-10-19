package br.senai.sc.EditoraDeLivros.model.factory;

import br.senai.sc.EditoraDeLivros.model.entities.Autor;
import br.senai.sc.EditoraDeLivros.model.entities.Diretor;
import br.senai.sc.EditoraDeLivros.model.entities.Pessoa;
import br.senai.sc.EditoraDeLivros.model.entities.Revisor;

public class PessoaFactory {
    public static Pessoa getPessoa(int tipoPessoa) {
        switch (tipoPessoa) {
            case 0:
                return new Autor();
            case 1:
                return new Revisor();
            case 2:
                return new Diretor();
            default:
                return null;
        }
    }
}
