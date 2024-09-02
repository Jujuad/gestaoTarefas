package br.com.fiap.gestaoTarefas.service;

import br.com.fiap.gestaoTarefas.model.user.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${api.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {
        long agora = System.currentTimeMillis();
        Date dataExpiracao = new Date(agora + 3600000); // 1 hora

        return Jwts.builder()
                .setSubject(usuario.getEmail())
                .setIssuedAt(new Date(agora))
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getUsuarioEmail(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

