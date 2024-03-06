package br.org.abmnet.abmedicina.service;

import br.org.abmnet.abmedicina.error.exception.ForbiddenTarefaException;
import br.org.abmnet.abmedicina.error.exception.TarefaNotFoundException;
import br.org.abmnet.abmedicina.model.Tarefa;
import br.org.abmnet.abmedicina.model.Usuario;
import br.org.abmnet.abmedicina.repository.TarefaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    public Tarefa create(Tarefa tarefa) {
        Usuario usuario = usuarioService.authenticated();
        tarefa.setUsuario(usuario);
        return repository.save(tarefa);
    }

    public List<Tarefa> readAll() {
        Usuario usuario = usuarioService.authenticated();
        List<Tarefa> allByUser = repository.findAllByUsuario(usuario);
        if (allByUser.isEmpty()) {
            return Collections.emptyList();
        }
        return allByUser;
    }

    public Tarefa readById(Long id) {
        Usuario usuario = usuarioService.authenticated();
        Optional<Tarefa> tarefa = repository.findByIdAndUsuarioId(id, usuario.getId());
        return tarefa.orElseThrow(() -> new ForbiddenTarefaException("Você não está autorizado a acessar esse recurso"));
    }

    public void update(Tarefa tarefa) {
        Tarefa existingTodo = this.readById(tarefa.getId());

        Tarefa updatedTarefa = Tarefa.builder()
                .id(existingTodo.getId())
                .titulo(tarefa.getTitulo())
                .descricao(tarefa.getDescricao())
                .usuario(existingTodo.getUsuario())
                .build();

        repository.save(updatedTarefa);
    }

    public void delete(Long id) {
        Tarefa existingTarefa = this.readById(id);
        repository.delete(existingTarefa);
    }
}
