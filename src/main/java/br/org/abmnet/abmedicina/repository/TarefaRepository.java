package br.org.abmnet.abmedicina.repository;

import br.org.abmnet.abmedicina.model.Tarefa;
import br.org.abmnet.abmedicina.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    Optional<Tarefa> findByIdAndUsuarioId(Long id, Long usuarioId);

    List<Tarefa> findAllByUsuario(Usuario usuario);
}
