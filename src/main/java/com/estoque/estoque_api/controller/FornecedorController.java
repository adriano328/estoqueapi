package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.dto.fornecedor.FornecedorAtualizaDto;
import com.estoque.estoque_api.dto.fornecedor.FornecedorDto;
import com.estoque.estoque_api.model.Fornecedor;
import com.estoque.estoque_api.service.FornecedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("fornecedor")
@RestController
@RequiredArgsConstructor
public class FornecedorController {

    private final FornecedorService fornecedorService;
    private final ModelMapper modelMapper;

    @Operation(summary = "Cadastra novo fornecedor", description = "Cadastra novo fornecedor")
    @PostMapping()
    ResponseEntity<FornecedorDto> salvarFornecedor(@Valid @RequestBody FornecedorDto fornecedorDto) {
        Fornecedor fornecedor = modelMapper.map(fornecedorDto, Fornecedor.class);
        fornecedorService.salvar(fornecedor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Busca fornecedor por CNPJ", description = "Busca fornecedor por CNPJ")
    @GetMapping("/buscar-por-cnpj")
    public ResponseEntity<Fornecedor> buscarPorCnpj(@RequestParam String cnpj) {
        Fornecedor fornecedor = fornecedorService.buscarPorCnpj(cnpj);
        return ResponseEntity.ok(fornecedor);
    }

    @Operation(summary = "Exclui fornecedor.", description = "Exclui fornecedor.")
    public void ExcluiFornecedor(@RequestParam Long id) {
        fornecedorService.ExcluiFornecedor(id);
    }

    @Operation(summary = "Atualiza fornecedor.", description = "Atuaiza fornecedor de produto.")
    ResponseEntity<FornecedorAtualizaDto> AtualizarFornecedor(@Valid @RequestBody FornecedorAtualizaDto fornecedorAtualizaDto) {
        Fornecedor fornecedor = modelMapper.map(fornecedorAtualizaDto, Fornecedor.class);
        fornecedorService.atualizaFornecedor(fornecedorAtualizaDto.getId(), fornecedor);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
