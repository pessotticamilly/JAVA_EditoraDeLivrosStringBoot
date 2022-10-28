package br.senai.sc.EditoraDeLivros.util;

import br.senai.sc.EditoraDeLivros.dto.LivroDto;
import br.senai.sc.EditoraDeLivros.model.entities.Livro;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.Valid;

public class LivroUtil {
    private final ObjectMapper mapper = new ObjectMapper();

    public Livro convertJsonToModel(String livroJson) {
        LivroDto livroDto = convertJsonToDto(livroJson);
        return convertDtoToModel(livroDto);

    }

    private LivroDto convertJsonToDto(String livroJson) {
        try {
            return this.mapper.readValue(livroJson, LivroDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Livro convertDtoToModel(@Valid LivroDto livroDto) {
        return this.mapper.convertValue(livroDto, Livro.class);
    }

}
