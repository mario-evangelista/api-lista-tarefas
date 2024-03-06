package br.org.abmnet.abmedicina.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDetails {
    @Schema(type = "string", example = "Mensagem de erro.")
    private String message;
}

