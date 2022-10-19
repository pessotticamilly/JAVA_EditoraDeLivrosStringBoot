package br.senai.sc.EditoraDeLivros.dto;

import br.senai.sc.EditoraDeLivros.model.entities.Autor;
import lombok.Getter;

import java.util.List;

@Getter
public class LivroDto {
    private Long isbn;
    private String titulo;
    private Integer qntdPaginas;
    private List<Autor> autores;
}
