package br.senai.sc.EditoraDeLivros.security;

import br.senai.sc.EditoraDeLivros.model.entities.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoFiltro extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        AutenticacaoService autenticacaoService = null;

        String token = request.getHeader("Authorization");

        if (token.startsWith("Bearer ")) {
            token = token.substring(7, token.length());
        } else {
            token = null;
        }

        Boolean valido = new AutenticacaoService().validarToken(token);

        if(valido) {
            Pessoa usuario = autenticacaoService.getUsuario(token);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(usuario.getUsername(), null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        } else {
            response.setStatus(401);
        }

        filterChain.doFilter(request, response);
    }
}
