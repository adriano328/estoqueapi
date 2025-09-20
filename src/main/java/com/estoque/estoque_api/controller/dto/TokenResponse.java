package com.estoque.estoque_api.controller.dto;

public record TokenResponse(String token, String tokenType, long expiresInMs) {}

