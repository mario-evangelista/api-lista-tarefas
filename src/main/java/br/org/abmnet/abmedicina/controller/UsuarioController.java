package br.org.abmnet.abmedicina.controller;

import br.org.abmnet.abmedicina.mapper.UsuarioMapper;
import br.org.abmnet.abmedicina.model.Usuario;
import br.org.abmnet.abmedicina.model.request.UsuarioRequestBody;
import br.org.abmnet.abmedicina.model.response.UsuarioResponseBody;
import br.org.abmnet.abmedicina.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping(value = "/usuarios")
public class UsuarioController implements UsuarioControllerAPI {


    private final UsuarioService service;

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signIn(@RequestBody UsuarioRequestBody usuarioRequestBody) {
        Usuario usuario = UsuarioMapper.INSTANCE.toUser(usuarioRequestBody);
        Usuario createdUsuario = service.signIn(usuario);
        UsuarioResponseBody usuarioResponseBody = UsuarioMapper.INSTANCE.toUserResponseBody(createdUsuario);
        return new ResponseEntity<>(usuarioResponseBody, HttpStatus.CREATED);
    }
}
