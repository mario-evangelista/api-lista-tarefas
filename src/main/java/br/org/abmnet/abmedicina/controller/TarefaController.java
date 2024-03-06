package br.org.abmnet.abmedicina.controller;

import br.org.abmnet.abmedicina.mapper.TarefaMapper;
import br.org.abmnet.abmedicina.model.Tarefa;
import br.org.abmnet.abmedicina.model.request.TarefaPostRequestBody;
import br.org.abmnet.abmedicina.model.request.TarefaPutRequestBody;
import br.org.abmnet.abmedicina.model.response.TarefaResponseBody;
import br.org.abmnet.abmedicina.service.TarefaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping(value = "/tarefas")
public class TarefaController implements TarefaControllerAPI {
    private final TarefaService service;

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody TarefaPostRequestBody mainPostRequestBody) {
        Tarefa main = TarefaMapper.INSTANCE.toTarefa(mainPostRequestBody);
        Tarefa createdMain = service.create(main);
        TarefaResponseBody tarefaResponseBody = TarefaMapper.INSTANCE.toMainResponseBody(createdMain);
        return new ResponseEntity<>(tarefaResponseBody, HttpStatus.CREATED);
    }

    @Override()
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> readAll() {
        List<TarefaResponseBody> tarefas = new ArrayList<>();
        service.readAll().forEach(main -> {
            TarefaResponseBody tarefaResponseBody = TarefaMapper.INSTANCE.toMainResponseBody(main);
            tarefas.add(tarefaResponseBody);
        });
        return new ResponseEntity<>(tarefas, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> readById(@PathVariable("id") Long id) {
        Tarefa main = service.readById(id);
        TarefaResponseBody mainResponseBody = TarefaMapper.INSTANCE.toMainResponseBody(main);
        return new ResponseEntity<>(mainResponseBody, HttpStatus.OK);
    }

    @Override
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@Valid @RequestBody TarefaPutRequestBody tarefaPutRequestBody) {
        Tarefa tarefa = TarefaMapper.INSTANCE.toTarefa(tarefaPutRequestBody);
        service.update(tarefa);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
