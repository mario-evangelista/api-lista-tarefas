package br.org.abmnet.abmedicina.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
@Tag(name = "tarefas")
public class TarefaPostRequestBody implements Serializable {

    @Size(min = 3, max = 100, message = "Um título deve ter entre 5 e 100 caracteres.")
    @NotEmpty(message = "O título é obrigatório")
    @NotNull(message = "O título é obrigatório")
    @Schema(type = "string", example = "Ler um capítulo de um livro")
    private String titulo;

    @NotEmpty(message = "A descrição é obrigatória")
    @NotNull(message = "A descrição é obrigatória")
    @Schema(type = "string", example = "Ler o segundo capítulo do livro Java For Dumies")
    private String descricao;
}
