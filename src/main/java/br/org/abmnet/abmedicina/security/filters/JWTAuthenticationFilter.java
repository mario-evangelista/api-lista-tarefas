package br.org.abmnet.abmedicina.security.filters;

import br.org.abmnet.abmedicina.error.exception.AuthenticationException;
import br.org.abmnet.abmedicina.mapper.JSONMapper;
import br.org.abmnet.abmedicina.model.Usuario;
import br.org.abmnet.abmedicina.model.request.UsuarioRequestBody;
import br.org.abmnet.abmedicina.service.JWTService;
import br.org.abmnet.abmedicina.service.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private JWTService jwtService;
    private UsuarioService usuarioService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTService jwtService, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Authentication authentication;
        try {
            UsuarioRequestBody usuarioRequestBody = new ObjectMapper().readValue(request.getInputStream(), UsuarioRequestBody.class);
            usuarioService.loadUserByUsername(usuarioRequestBody.getEmail());
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuarioRequestBody.getEmail(), usuarioRequestBody.getSenha(), new ArrayList<>());
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (IOException ioException) {
            throw new AuthenticationException("Falha ao autenticar o usuário " + ioException.getMessage());
        }
        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String username = ((Usuario) authResult.getPrincipal()).getUsername();
        String token = jwtService.createToken(username);
        Usuario usuario = usuarioService.setUserToken(username, token);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String usuarioResponseBody = JSONMapper.objectToJSON(usuario);
        response.getWriter().append(usuarioResponseBody);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException failed) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().append("{\"mensagem\": \"Usuário e/ou senha inválidos\"}");
    }
}
