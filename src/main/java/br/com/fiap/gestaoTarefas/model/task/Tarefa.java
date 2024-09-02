package br.com.fiap.gestaoTarefas.model.task;

import br.com.fiap.gestaoTarefas.model.user.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TB_TAREFA")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_tarefa")
    private Long id;

    @Column(name = "ds_titulo", nullable = false)
    private String titulo;

    @Column(name = "ds_descricao")
    private String descricao;

    @Column(name = "dt_conclusao_prevista", nullable = false)
    private String dataConclusaoPrevista; // Use LocalDate ou LocalDateTime se necessário

    @Column(name = "nm_status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "cd_usuario", nullable = false)
    private Usuario usuario;

    public Tarefa(String titulo, String descricao, LocalDate dataConclusaoPrevista, String status, Usuario usuario) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataConclusaoPrevista = String.valueOf(dataConclusaoPrevista);
        this.status = status;
        this.usuario = usuario;
    }

    public Tarefa(String titulo, String descricao, String dataConclusaoPrevista, String pendente, Usuario usuario) {
    }
}

