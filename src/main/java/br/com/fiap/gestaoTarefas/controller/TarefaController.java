package br.com.fiap.gestaoTarefas.controller;

import br.com.fiap.gestaoTarefas.dto.tarefa.TarefaDto;
import br.com.fiap.gestaoTarefas.model.task.Tarefa;
import br.com.fiap.gestaoTarefas.repository.TarefaRepository;
import br.com.fiap.gestaoTarefas.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@Valid @RequestBody TarefaDto tarefaDto,
                                              @AuthenticationPrincipal UserDetails usuario) {
        Tarefa tarefa = tarefaService.criarTarefa(tarefaDto, usuario.getUsername());
        return new ResponseEntity<>(tarefa, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> visualizarTarefas(@AuthenticationPrincipal UserDetails usuario) {
        List<Tarefa> tarefas = tarefaService.visualizarTarefas(usuario.getUsername());
        return ResponseEntity.ok(tarefas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id,
                                                  @Valid @RequestBody TarefaDto tarefaDto,
                                                  @AuthenticationPrincipal UserDetails usuario) {
        Tarefa tarefa = tarefaService.atualizarTarefa(id, tarefaDto, usuario.getUsername());
        return ResponseEntity.ok(tarefa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTarefa(@PathVariable Long id,
                                              @AuthenticationPrincipal UserDetails usuario) {
        tarefaService.excluirTarefa(id, usuario.getUsername());
        return ResponseEntity.noContent().build();
    }
}
