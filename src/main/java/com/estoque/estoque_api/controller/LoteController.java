package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.dto.categoria.CategoriaDto;
import com.estoque.estoque_api.dto.lote.LoteAtualizaDto;
import com.estoque.estoque_api.dto.lote.LoteDto;
import com.estoque.estoque_api.model.Lote;
import com.estoque.estoque_api.service.LoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("lote")
@RequiredArgsConstructor
public class LoteController {

    private final LoteService loteService;
    private final ModelMapper modelMapper;

    @Operation(summary = "Cadastra novo lote", description = "Cadastra novo lote")
    @PostMapping
    ResponseEntity<LoteDto> salvarLote(@Valid @RequestBody LoteDto loteDto) {
        Lote lote = modelMapper.map(loteDto, Lote.class);
        loteService.salvar(lote);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Busca lote", description = "Busca lote")
    @GetMapping("buscar-lote")
    ResponseEntity<LoteDto> buscarPorLote(@RequestParam Long id) {
        Lote lote = loteService.BuscarPorLote(id);
        LoteDto loteDto = modelMapper.map(lote, LoteDto.class);
        return ResponseEntity.ok(loteDto);
    }

    @Operation(summary = "Exclui lote", description = "Exclui lote")
    @GetMapping("/excluir")
    public void DeletaLote(@RequestParam Long id) {
        loteService.deletaLote(id);
    }

    @Operation(summary = "Atualiza lote", description = "Atualiza lote")
    @PutMapping()
    ResponseEntity<LoteAtualizaDto> AtualizaLote(@Valid @RequestBody LoteAtualizaDto loteAtualizaDto) {
        Lote lote = modelMapper.map(loteAtualizaDto, Lote.class);
        loteService.AtualizarLote(lote.getId(), lote);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
