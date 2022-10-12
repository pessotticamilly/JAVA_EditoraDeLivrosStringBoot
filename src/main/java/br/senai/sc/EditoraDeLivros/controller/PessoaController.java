package br.senai.sc.EditoraDeLivros.controller;

import br.senai.sc.EditoraDeLivros.dto.PessoaDto;
import br.senai.sc.EditoraDeLivros.model.entities.Pessoa;
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
@RequestMapping("/EditoraDeLivros/pessoa")
public class PessoaController {
    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findAll());
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid PessoaDto pessoaDto) {
        if (pessoaService.existById(pessoaDto.getCpf())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF já cadastrado");
        }

        if (pessoaService.existByEmail(pessoaDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado");
        }

        Pessoa pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaDto, pessoa);

        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.save(pessoa));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Object> findById(@PathVariable(value = "cpf") Long cpf) {
        if (pessoaService.existById(cpf)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma pessoa com o CPF informado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findById(cpf));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "cpf") Long cpf) {
        if (pessoaService.existById(cpf)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma pessoa com o CPF informado");
        }

        pessoaService.deleteById(cpf);
        return ResponseEntity.status(HttpStatus.OK).body("Pessoa deletada com sucesso");
    }

    @GetMapping("/{email}")
    public ResponseEntity<Object> findByEmail(@PathVariable(value = "email") String email) {
        if (pessoaService.existByEmail(email)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma pessoa com o email informado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findByEmail(email));
    }
}
