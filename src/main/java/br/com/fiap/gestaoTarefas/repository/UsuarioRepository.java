package br.com.fiap.gestaoTarefas.repository;

import br.com.fiap.gestaoTarefas.model.user.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository {
    Usuario findByEmail(String email);

    Usuario findByLogin(String loginUsuario);

    Usuario save(Usuario usuario);
}
