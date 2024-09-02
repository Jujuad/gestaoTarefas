package br.com.fiap.gestaoTarefas.dto.tarefa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class TarefaDto {

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    private String descricao;

    @NotNull(message = "Data de conclusão prevista é obrigatória")
    private LocalDate dataConclusaoPrevista;

    @NotBlank(message = "Status é obrigatório")
    private String status;

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataConclusaoPrevista() {
        return dataConclusaoPrevista;
    }

    public void setDataConclusaoPrevista(LocalDate dataConclusaoPrevista) {
        this.dataConclusaoPrevista = dataConclusaoPrevista;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
