package br.com.fiap.gestaoTarefas.repository;

import br.com.fiap.gestaoTarefas.model.task.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository {
    List<Tarefa> findByUsuarioId(Long usuarioId);

    ScopedValue<Object> findById(Long id);

    Tarefa save(Tarefa tarefa);

    void delete(Tarefa tarefa);
}
