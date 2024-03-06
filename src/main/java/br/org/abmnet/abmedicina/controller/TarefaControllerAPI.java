package br.org.abmnet.abmedicina.controller;

import br.org.abmnet.abmedicina.error.ErrorDetails;
import br.org.abmnet.abmedicina.model.request.TarefaPostRequestBody;
import br.org.abmnet.abmedicina.model.request.TarefaPutRequestBody;
import br.org.abmnet.abmedicina.model.response.TarefaResponseBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "tarefas")
public interface TarefaControllerAPI {

    @Operation(summary = "Cadastra uma nova tarefa", description = "Realiza o cadastro de uma nova tarefa no banco de dados.", tags = "tarefas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarefa cadastrada com sucesso.",
                    content = @Content(schema = @Schema(implementation = TarefaResponseBody.class))),
            @ApiResponse(responseCode = "400",
                    description = "Bad request body inválido",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
            @ApiResponse(responseCode = "403",
                    description = "Você não está autorizado a utilizar esse recurso"),
            @ApiResponse(responseCode = "415",
                    description = "Mídia não suportada, por favor utilize o MediaType application/json",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
    })
    ResponseEntity<?> create(TarefaPostRequestBody todoPostRequestBody);

    @Operation(summary = "Recupera todas as tarefas cadastradas.", description = "Recupera todas as tarefas cadastradas no banco de dados.", tags = "tarefas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa recuperadas com sucesso.", content = @Content(schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "403",
                    description = "Você não está autorizado a utilizar esse recurso"),
            @ApiResponse(responseCode = "415",
                    description = "Mídia não suportada, por favor utilize o MediaType application/json",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
    })
    ResponseEntity<?> readAll();

    @Operation(summary = "Recupera uma tarefa pelo id.", description = "Dado um id recupera uma tarefa do banco de dados.", tags = "tarefas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa recuperada com sucesso.", content = @Content(schema = @Schema(implementation = TarefaResponseBody.class))),
            @ApiResponse(responseCode = "403",
                    description = "Você não está autorizado a utilizar esse recurso"),
            @ApiResponse(responseCode = "404",
                    description = "Tarefa não encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
            @ApiResponse(responseCode = "415",
                    description = "Mídia não suportada, por favor utilize o MediaType application/json",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
    })
    ResponseEntity<?> readById(Long id);

    @Operation(summary = "Atualiza uma tarefa.", description = "Atualiza uma tarefa cadastrada no banco de dados.", tags = "tarefas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tarefa atualizada com sucesso."),
            @ApiResponse(responseCode = "403",
                    description = "Você não está autorizado a utilizar esse recurso"),
            @ApiResponse(responseCode = "404",
                    description = "Tarefa não encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
            @ApiResponse(responseCode = "400",
                    description = "Bad request body inválido",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
            @ApiResponse(responseCode = "415",
                    description = "Mídia não suportada, por favor utilize o MediaType application/json",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
    })
    ResponseEntity<?> update(TarefaPutRequestBody todoPutRequestBody);

    @Operation(summary = "Excluí tarefa.", description = "Excluí uma tarefa cadastrada no banco de dados.", tags = "tarefas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tarefa excluída com sucesso."),
            @ApiResponse(responseCode = "403",
                    description = "Você não está autorizado a utilizar esse recurso"),
            @ApiResponse(responseCode = "404",
                    description = "Tarefa não encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
            @ApiResponse(responseCode = "415",
                    description = "Mídia não suportada, por favor utilize o MediaType application/json",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
    })
    ResponseEntity<?> delete(Long id);

}
