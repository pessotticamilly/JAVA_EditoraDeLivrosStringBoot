package br.senai.sc.EditoraDeLivros.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<Object> autenticacao(@RequestBody @Valid UsuarioDto usuarioDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuarioDto.getEmail(), usuarioDto.getSenha());

        System.out.println("Antes - " + authenticationToken);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        System.out.println("Depois - " + authentication.isAuthenticated());

        if (authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.OK).body("Usuário autenticado com sucesso");
        }

        return ResponseEntity.badRequest().build();
    }
}