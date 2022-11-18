package br.senai.sc.EditoraDeLivros.controller;

import br.senai.sc.EditoraDeLivros.dto.PessoaDto;
import br.senai.sc.EditoraDeLivros.model.entities.Pessoa;
import br.senai.sc.EditoraDeLivros.model.factory.PessoaFactory;
import br.senai.sc.EditoraDeLivros.model.service.PessoaService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/editoradelivros/tb_pessoa")
public class PessoaController {
    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Pessoa>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findAll());
    }

    @GetMapping("/listar/{cpf}")
    public ResponseEntity<Object> findById(@PathVariable(value = "cpf") Long cpf) {
        if (!pessoaService.existById(cpf)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma pessoa com o CPF informado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findById(cpf));
    }

    @GetMapping("/listar/{email}")
    public ResponseEntity<Object> findByEmail(@PathVariable(value = "email") String email) {
        if (!pessoaService.existByEmail(email)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma pessoa com o email informado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findByEmail(email));
    }

    @PostMapping("/cadastrar/{dtype}")
    public ResponseEntity<Object> save(@PathVariable(value = "dtype") int dtype, @RequestBody @Valid PessoaDto pessoaDto) {
        if (pessoaService.existById(pessoaDto.getCpf())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF já cadastrado");
        }

        if (pessoaService.existByEmail(pessoaDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado");
        }

        Pessoa pessoa = PessoaFactory.getPessoa(dtype);
        BeanUtils.copyProperties(pessoaDto, pessoa);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        pessoa.setSenha(encoder.encode(pessoa.getSenha()));

        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.save(pessoa));
    }

    @PutMapping("/editar/{cpf}")
    public ResponseEntity<Object> update(@PathVariable(value = "cpf") Long cpf, @Valid @RequestBody PessoaDto pessoaDto) {
        if (!pessoaService.existById(cpf)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma pessoa com o CPF informado");
        }

        Pessoa pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaDto, pessoa);
        pessoa.setCpf(cpf);

        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.save(pessoa));
    }

    @DeleteMapping("/excluir/{cpf}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "cpf") Long cpf) {
        if (!pessoaService.existById(cpf)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma pessoa com o CPF informado");
        }

        pessoaService.deleteById(cpf);
        return ResponseEntity.status(HttpStatus.OK).body("Pessoa deletada com sucesso");
    }
}
