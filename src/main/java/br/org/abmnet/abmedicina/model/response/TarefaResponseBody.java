package br.org.abmnet.abmedicina.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.io.Serializable;

@Data
@Tag(name = "tarefas")
public class TarefaResponseBody implements Serializable {

    @Schema(type = "long", example = "1")
    private Long id;

    @Schema(type = "string", example = "Ler um capítulo de um livro")
    private String titulo;

    @Schema(type = "string", example = "Ler o segundo capítulo do livro Java For Dumies")
    private String descricao;

}
