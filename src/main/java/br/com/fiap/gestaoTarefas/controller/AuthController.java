package br.com.fiap.gestaoTarefas.controller;

import br.com.fiap.gestaoTarefas.dto.usuario.CadastroUsuarioDto;
import br.com.fiap.gestaoTarefas.dto.usuario.LoginDto;
import br.com.fiap.gestaoTarefas.model.user.Usuario;
import br.com.fiap.gestaoTarefas.service.AutenticacaoService;
import br.com.fiap.gestaoTarefas.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<Usuario> registrar(@Valid @RequestBody CadastroUsuarioDto usuarioDto) {
        Usuario usuario = autenticacaoService.registrar(usuarioDto);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDto loginDto) {
        String token = autenticacaoService.login(loginDto);
        return ResponseEntity.ok(token);
    }
}

