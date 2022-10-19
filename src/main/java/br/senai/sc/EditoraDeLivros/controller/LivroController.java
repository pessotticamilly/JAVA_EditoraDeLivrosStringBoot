package br.senai.sc.EditoraDeLivros.controller;

import br.senai.sc.EditoraDeLivros.dto.LivroDto;
import br.senai.sc.EditoraDeLivros.model.entities.Autor;
import br.senai.sc.EditoraDeLivros.model.entities.Livro;
import br.senai.sc.EditoraDeLivros.model.entities.Status;
import br.senai.sc.EditoraDeLivros.model.service.LivroService;
import br.senai.sc.EditoraDeLivros.model.service.PessoaService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/editoradelivro/tb_livro")
public class LivroController {
    private final LivroService livroService;
    private final PessoaService pessoaService;

    public LivroController(LivroService pessoaService, PessoaService pessoaService1) {
        this.livroService = pessoaService;
        this.pessoaService = pessoaService1;
    }

    @GetMapping("/listar/isbn/{isbn}")
    public ResponseEntity<Object> findById(@PathVariable(value = "isbn") Long isbn) {
        Optional<Livro> livroOptional = livroService.findById(isbn);
        if (livroOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum livro com o ISBN informado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(livroOptional);
    }

    @GetMapping("/listar/status/{status}")
    public ResponseEntity<Object> findByStatus(@PathVariable(value = "status") Status status) {
        List<Livro> livroOptional = livroService.findByStatus(status);

        if (livroOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum livro com o status informado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(livroOptional);
    }

    @GetMapping("/listar/autor/{cpf}")
    public ResponseEntity<Object> findByAutor(@PathVariable(value = "cpf") Autor autor) {
        if (!pessoaService.existById(autor.getCpf())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum autor com o CPF informado");
        }

        List<Livro> livroOptional = livroService.findByAutor(autor);

        if (livroOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum livro com o autor informado");
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
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ISBN já cadastrado");
        }

        for(int i = 0; i < livroDto.getAutores().size(); i++) {
            if(!pessoaService.existById(livroDto.getAutores().get(i).getCpf())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Autor não encontrado!");
            }
        }

        Livro livro = new Livro();
        BeanUtils.copyProperties(livroDto, livro);
        livro.setStatus(Status.AGUARDANDO_REVISAO);

        return ResponseEntity.status(HttpStatus.OK).body(livroService.save(livro));
    }

    @PutMapping("/editar/{isbn}")
    public ResponseEntity<Object> update(@PathVariable(value = "isbn") Long isbn, @Valid @RequestBody LivroDto livroDto) {
        if (!livroService.existById(isbn)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum livro com o ISBN informado");
        }

        Livro livro = livroService.findById(livroDto.getIsbn()).get();
        BeanUtils.copyProperties(livroDto, livro);

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