package br.com.fiap.gestaoTarefas.service;

import br.com.fiap.gestaoTarefas.dto.tarefa.TarefaDto;
import br.com.fiap.gestaoTarefas.model.task.Tarefa;
import br.com.fiap.gestaoTarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public void criarTarefa(TarefaDto tarefaDto) {
        Tarefa tarefa = new Tarefa(tarefaDto.getTitulo(), tarefaDto.getDescricao(),
                tarefaDto.getDataConclusaoPrevista(), tarefaDto.getStatus());
        tarefaRepository.save(tarefa);
    }

    public List<Tarefa> visualizarTarefas() {
        return tarefaRepository.findAll();
    }

    public Tarefa obterTarefaPorId(Long id) {
        return (Tarefa) tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    public void atualizarTarefa(Long id, TarefaDto tarefaDto) {
        Tarefa tarefa = obterTarefaPorId(id);
        tarefa.setTitulo(tarefaDto.getTitulo());
        tarefa.setDescricao(tarefaDto.getDescricao());
        tarefa.setDataConclusaoPrevista(tarefaDto.getDataConclusaoPrevista());
        tarefa.setStatus(tarefaDto.getStatus());
        tarefaRepository.save(tarefa);
    }

    public void excluirTarefa(Long id) {
        Tarefa tarefa = obterTarefaPorId(id);
        tarefaRepository.delete(tarefa);
    }

    public List<Tarefa> pesquisarTarefas(String criterio) {
        return tarefaRepository.findByTituloContaining(criterio);
    }
}
