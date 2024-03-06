package br.org.abmnet.abmedicina.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
@Tag(name = "usuarios")
public class UsuarioRequestBody {
    @Email
    @NotEmpty(message = "O e-mail é obrigatório")
    @NotNull(message = "O e-mail é obrigatório")
    @Schema(type = "string", example = "seu.email@provedor.com")
    private String email;

    @NotEmpty(message = "A senha é obrigatória")
    @NotNull(message = "A senha é obrigatória")
    @Schema(type = "string", example = "suaSenhaMuitoForte!")
    private String senha;
}
