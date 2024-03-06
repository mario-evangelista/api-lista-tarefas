package br.org.abmnet.abmedicina.mapper;

import br.org.abmnet.abmedicina.model.Tarefa;
import br.org.abmnet.abmedicina.model.request.TarefaPostRequestBody;
import br.org.abmnet.abmedicina.model.request.TarefaPutRequestBody;
import br.org.abmnet.abmedicina.model.response.TarefaResponseBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class TarefaMapper {
    public static final TarefaMapper INSTANCE = Mappers.getMapper(TarefaMapper.class);

    public abstract Tarefa toTarefa(TarefaPostRequestBody tarefaPostRequestBody);

    public abstract Tarefa toTarefa(TarefaPutRequestBody tarefaPutRequestBody);

    public abstract TarefaResponseBody toMainResponseBody(Tarefa tarefa);

}
