package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.dto.LoginRequest;
import com.estoque.estoque_api.dto.TokenResponse;
import com.estoque.estoque_api.service.TokenService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authManager, TokenService tokenService) {
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest req) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(req.username(), req.password());
            authManager.authenticate(authToken); // valida contra o banco
            String jwt = tokenService.generateToken(req.username());
            return new TokenResponse(jwt, "Bearer", 3600000L);
        } catch (AuthenticationException ex) {
            throw new BadCredentialsException("Credenciais inválidas");
        }
    }
}
