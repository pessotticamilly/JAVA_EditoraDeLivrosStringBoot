package br.senai.sc.EditoraDeLivros.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class AutenticacaoController {
    @PostMapping
    public ResponseEntity<Object> autenticacao(@RequestBody @Valid UsuarioDto usuarioDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuarioDto.getEmail(), usuarioDto.getSenha());

        if (authenticationToken.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.OK).body("Usuário autenticado com sucesso");
        }

        return ResponseEntity.badRequest().build();
    }
}