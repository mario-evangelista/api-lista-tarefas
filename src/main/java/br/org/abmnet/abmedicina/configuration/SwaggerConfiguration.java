package br.org.abmnet.abmedicina.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    private final License apiLicense = new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0");
    private final Info apiInformation = new Info().title("Lista de Tarefas API").description("Lista de Tarefas API").version("v1").license(apiLicense);

    @Bean
    public OpenAPI todoListApi() {
        String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components().addSecuritySchemes(securitySchemeName, new SecurityScheme().name(securitySchemeName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")))
                .info(apiInformation)
                .externalDocs(new ExternalDocumentation()
                        .description("Spring docs")
                        .url("https://springdoc.org/#Introduction"));
    }
}
