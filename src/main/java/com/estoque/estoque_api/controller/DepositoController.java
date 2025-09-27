package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.dto.deposito.DepositoAtualizaDto;
import com.estoque.estoque_api.dto.deposito.DepositoDto;
import com.estoque.estoque_api.model.Deposito;
import com.estoque.estoque_api.service.DepositoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("deposito")
@RestController
@RequiredArgsConstructor
public class DepositoController {

    private final DepositoService depositoService;
    private final ModelMapper modelMapper;

    @Operation(summary = "Cadastra novo deposito", description = "Cadastra novo deposito")
    @PostMapping
    ResponseEntity<DepositoDto> salvarDeposito(@Valid @RequestBody DepositoDto depositoDto) {
        Deposito deposito = modelMapper.map(depositoDto, Deposito.class);
        depositoService.salvar(deposito);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Busca deposito", description = "Busca deposito de produto.")
    @GetMapping("busca-deposito")
    ResponseEntity<DepositoDto> buscaDeposito(@RequestParam Long id) {
        Deposito deposito = depositoService.buscarDepositoPorId(id);
        DepositoDto depositoDto = modelMapper.map(deposito, DepositoDto.class);
        return ResponseEntity.ok(depositoDto);
    }

    @Operation(summary = "Deleta deposito", description = "Deleta deposito de produto.")
    @DeleteMapping("/excluir")
    public void DeletaDeposito(@RequestParam Long id) {
        depositoService.DeletaDeposito(id);
    }

    @Operation(summary = "Atualiza deposito", description = "Atualiza deposito de produto.")
    @PutMapping()
    ResponseEntity<DepositoAtualizaDto> atualizaDeposito(@Valid @RequestBody DepositoAtualizaDto depositoAtualiza) {
        Deposito deposito = depositoService.buscarDepositoPorId(depositoAtualiza.getId());
        depositoService.atualizarDeposito(depositoAtualiza.getId(), deposito);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
