package br.org.abmnet.abmedicina.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.io.Serializable;

@Data
@Tag(name = "usuarios")
public class UsuarioResponseBody implements Serializable {
    @Schema(type = "number", example = "1")
    private Long id;
    @Schema(type = "String", example = "seu.email@provedor.com")
    private String email;
}

