package com.estoque.estoque_api.dto.autenticacao;

public record TokenResponse(String token, String tokenType, long expiresInMs) {}

