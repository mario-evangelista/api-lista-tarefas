package br.org.abmnet.abmedicina.controller;

import br.org.abmnet.abmedicina.error.ErrorDetails;
import br.org.abmnet.abmedicina.model.request.UsuarioRequestBody;
import br.org.abmnet.abmedicina.model.response.UsuarioResponseBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "usuarios")
public interface UsuarioControllerAPI {
    @Operation(summary = "Cadastra um novo usuário", description = "Realiza o cadastro de um novo usuário no banco de dados.", tags = "usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso.",
                    content = @Content(schema = @Schema(implementation = UsuarioResponseBody.class))),
            @ApiResponse(responseCode = "400",
                    description = "Bad request body inválido",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
            @ApiResponse(responseCode = "403",
                    description = "Você não está autorizado a utilizar esse recurso"),
            @ApiResponse(responseCode = "415",
                    description = "Mídia não suportada, por favor utilize o MediaType application/json",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
    })
    ResponseEntity<?> signIn(UsuarioRequestBody usuarioRequestBody);
}

