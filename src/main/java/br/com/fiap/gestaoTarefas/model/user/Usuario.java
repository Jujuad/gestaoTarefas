package br.com.fiap.gestaoTarefas.model.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TB_USUARIO", uniqueConstraints = @UniqueConstraint(columnNames = "ds_email"))
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_usuario")
    private Long id;

    @Column(name = "ds_nome", nullable = false)
    private String nome;

    @Column(name = "ds_email", nullable = false, unique = true)
    private String email;

    @Column(name = "ds_senha", nullable = false)
    private String senha;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    // Retornar o email como login
    public String getLogin() {
        return email;
    }
}
