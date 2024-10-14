package br.com.fiap.gestaoTarefas.repository;

import br.com.fiap.gestaoTarefas.model.task.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByTituloContaining(String titulo);
}
