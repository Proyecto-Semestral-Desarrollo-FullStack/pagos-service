package com.veterinaria.pagos.controller;

import com.veterinaria.pagos.dto.PagoRequest;
import com.veterinaria.pagos.dto.PagoResponse;
import com.veterinaria.pagos.service.PagoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    @PostMapping
    public ResponseEntity<PagoResponse> crear(@Valid @RequestBody PagoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pagoService.crear(request));
    }

    @GetMapping
    public ResponseEntity<List<PagoResponse>> listar() {
        return ResponseEntity.ok(pagoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pagoService.buscarPorId(id));
    }

    @GetMapping("/cita/{citaId}")
    public ResponseEntity<PagoResponse> buscarPorCita(@PathVariable Long citaId) {
        return ResponseEntity.ok(pagoService.buscarPorCita(citaId));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<PagoResponse>> listarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(pagoService.listarPorEstado(estado));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<PagoResponse> cambiarEstado(@PathVariable Long id,
                                                      @RequestParam String estado) {
        return ResponseEntity.ok(pagoService.cambiarEstado(id, estado));
    }

    @GetMapping("/existe/{id}")
    public ResponseEntity<Boolean> existePago(@PathVariable Long id) {
        return ResponseEntity.ok(pagoService.existePago(id));
    }
}