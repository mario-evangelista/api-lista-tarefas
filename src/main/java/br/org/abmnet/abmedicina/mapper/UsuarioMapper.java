package br.org.abmnet.abmedicina.mapper;

import br.org.abmnet.abmedicina.model.Usuario;
import br.org.abmnet.abmedicina.model.request.UsuarioRequestBody;
import br.org.abmnet.abmedicina.model.response.UsuarioResponseBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UsuarioMapper {
    public static final UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    public abstract Usuario toUser(UsuarioRequestBody usuarioRequestBody);

    public abstract UsuarioResponseBody toUserResponseBody(Usuario usuario);
}
