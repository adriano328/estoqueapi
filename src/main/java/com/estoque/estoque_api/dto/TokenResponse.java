package com.estoque.estoque_api.dto;

public record TokenResponse(String token, String tokenType, long expiresInMs) {}

