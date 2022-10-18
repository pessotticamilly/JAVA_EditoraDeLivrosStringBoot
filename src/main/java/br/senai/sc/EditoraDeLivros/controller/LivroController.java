package br.senai.sc.EditoraDeLivros.controller;

import br.senai.sc.EditoraDeLivros.dto.LivroDto;
import br.senai.sc.EditoraDeLivros.dto.PessoaDto;
import br.senai.sc.EditoraDeLivros.model.entities.Livro;
import br.senai.sc.EditoraDeLivros.model.entities.Pessoa;
import br.senai.sc.EditoraDeLivros.model.entities.Status;
import br.senai.sc.EditoraDeLivros.model.service.LivroService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/editoradelivros/tb_livro")
public class LivroController {
    private final LivroService livroService;

    public LivroController(LivroService pessoaService) {
        this.livroService = pessoaService;
    }

    @GetMapping("/listar/{isbn}")
    public ResponseEntity<Object> findById(@PathVariable(value = "isbn") Long isbn) {
        Optional<Livro> livroOptional = livroService.findById(isbn);
        if (livroOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum livro com o ISBN informado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(livroOptional);
    }

    @GetMapping("/listar/{status}")
    public ResponseEntity<Object> findByStatus(@PathVariable(value = "status") Status status) {
        List<Livro> livroOptional = livroService.findByStatus(status);

        if (livroOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum livro com o status informado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(livroOptional);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Livro>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.findAll());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Object> save(@RequestBody @Valid LivroDto livroDto) {
        if (livroService.existById(livroDto.getIsbn())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF já cadastrado");
        }

        Livro livro = new Livro();
        BeanUtils.copyProperties(livroDto, livro);

        return ResponseEntity.status(HttpStatus.OK).body(livroService.save(livro));
    }

    @PutMapping("/editar/{isbn}")
    public ResponseEntity<Object> update(@PathVariable(value = "isbn") Long isbn, @Valid @RequestBody LivroDto livroDto) {
        if (!livroService.existById(isbn)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum livro com o ISBN informado");
        }

        Livro livro = new Livro();
        BeanUtils.copyProperties(livroDto, livro);
        livro.setIsbn(isbn);

        return ResponseEntity.status(HttpStatus.OK).body(livroService.save(livro));
    }

    @DeleteMapping("/excluir/{isbn}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "isbn") Long isbn) {
        if (!livroService.existById(isbn)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum livro com o ISBN informado");
        }

        livroService.deleteById(isbn);
        return ResponseEntity.status(HttpStatus.OK).body("Livro deletado com sucesso");
    }
}