package br.com.fiap.gestaoTarefas.service;

import br.com.fiap.gestaoTarefas.dto.tarefa.TarefaDto;
import br.com.fiap.gestaoTarefas.model.task.Tarefa;
import br.com.fiap.gestaoTarefas.model.user.Usuario;
import br.com.fiap.gestaoTarefas.repository.TarefaRepository;
import br.com.fiap.gestaoTarefas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Tarefa criarTarefa(TarefaDto tarefaDto, String loginUsuario) {
        Usuario usuario = usuarioRepository.findByLogin(loginUsuario);
        Tarefa tarefa = new Tarefa(tarefaDto.getTitulo(), tarefaDto.getDescricao(),
                tarefaDto.getDataConclusaoPrevista(), "Pendente", usuario);
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> visualizarTarefas(String loginUsuario) {
        Usuario usuario = usuarioRepository.findByLogin(loginUsuario);
        return tarefaRepository.findByUsuarioId(usuario.getId());
    }

    public Tarefa atualizarTarefa(Long id, TarefaDto tarefaDto, String loginUsuario) {
        Tarefa tarefa = (Tarefa) tarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        if (!tarefa.getUsuario().getLogin().equals(loginUsuario)) {
            throw new RuntimeException("Acesso não permitido");
        }
        tarefa.setTitulo(tarefaDto.getTitulo());
        tarefa.setDescricao(tarefaDto.getDescricao());
        tarefa.setDataConclusaoPrevista(tarefaDto.getDataConclusaoPrevista());
        tarefa.setStatus(tarefaDto.getStatus());
        return tarefaRepository.save(tarefa);
    }

    public void excluirTarefa(Long id, String loginUsuario) {
        Tarefa tarefa = (Tarefa) tarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        if (!tarefa.getUsuario().getLogin().equals(loginUsuario)) {
            throw new RuntimeException("Acesso não permitido");
        }
        tarefaRepository.delete(tarefa);
    }
}
