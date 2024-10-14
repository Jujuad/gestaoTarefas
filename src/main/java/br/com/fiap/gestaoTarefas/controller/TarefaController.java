package br.com.fiap.gestaoTarefas.controller;

import br.com.fiap.gestaoTarefas.dto.tarefa.TarefaDto;
import br.com.fiap.gestaoTarefas.model.task.Tarefa;
import br.com.fiap.gestaoTarefas.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public String listarTarefas(Model model) {
        List<Tarefa> tarefas = tarefaService.visualizarTarefas();
        model.addAttribute("tarefas", tarefas);
        return "tarefas/list";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovo(Model model) {
        model.addAttribute("tarefa", new TarefaDto());
        return "tarefas/form";
    }

    @PostMapping
    public String criarTarefa(@Valid @ModelAttribute("tarefa") TarefaDto tarefaDto) {
        tarefaService.criarTarefa(tarefaDto);
        return "redirect:/tarefas";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Tarefa tarefa = tarefaService.obterTarefaPorId(id);
        model.addAttribute("tarefa", tarefa);
        return "tarefas/form";
    }

    @PostMapping("/{id}")
    public String atualizarTarefa(@PathVariable Long id, @Valid @ModelAttribute("tarefa") TarefaDto tarefaDto) {
        tarefaService.atualizarTarefa(id, tarefaDto);
        return "redirect:/tarefas";
    }

    @GetMapping("/{id}/excluir")
    public String excluirTarefa(@PathVariable Long id) {
        tarefaService.excluirTarefa(id);
        return "redirect:/tarefas";
    }

    @GetMapping("/pesquisar")
    public String pesquisarTarefas(@RequestParam String criterio, Model model) {
        List<Tarefa> tarefas = tarefaService.pesquisarTarefas(criterio);
        model.addAttribute("tarefas", tarefas);
        return "tarefas/search";
    }
}
