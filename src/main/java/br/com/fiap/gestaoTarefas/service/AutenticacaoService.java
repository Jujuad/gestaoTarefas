package br.com.fiap.gestaoTarefas.service;

import br.com.fiap.gestaoTarefas.dto.usuario.CadastroUsuarioDto;
import br.com.fiap.gestaoTarefas.dto.usuario.LoginDto;
import br.com.fiap.gestaoTarefas.model.user.Usuario;
import br.com.fiap.gestaoTarefas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    public Usuario registrar(CadastroUsuarioDto usuarioDto) {
        Usuario usuario = new Usuario(usuarioDto.getNome(), usuarioDto.getEmail(),
                passwordEncoder.encode(usuarioDto.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public String login(LoginDto loginDto) {
        Usuario usuario = usuarioRepository.findByEmail(loginDto.getEmail());
        if (usuario == null || !passwordEncoder.matches(loginDto.getSenha(), usuario.getSenha())) {
            throw new RuntimeException("Credenciais inválidas");
        }
        return tokenService.gerarToken(usuario);
    }
}
