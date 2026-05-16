package com.veterinaria.pagos.service;

import com.veterinaria.pagos.dto.PagoRequest;
import com.veterinaria.pagos.dto.PagoResponse;
import com.veterinaria.pagos.exception.ResourceNotFoundException;
import com.veterinaria.pagos.model.Pago;
import com.veterinaria.pagos.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PagoService {

    private final PagoRepository pagoRepository;
    private final CitaClientService citaClientService;

    public PagoResponse crear(PagoRequest request) {

        if (!citaClientService.existeCita(request.getCitaId())) {
            throw new IllegalArgumentException(
                    "La cita con id " + request.getCitaId() + " no existe");
        }

        if (pagoRepository.existsByCitaId(request.getCitaId())) {
            throw new IllegalArgumentException(
                    "La cita con id " + request.getCitaId() + " ya tiene un pago registrado");
        }

        Pago pago = Pago.builder()
                .montoTotal(request.getMontoTotal())
                .metodoPago(request.getMetodoPago())
                .estado("PENDIENTE")
                .citaId(request.getCitaId())
                .build();

        return toResponse(pagoRepository.save(pago));
    }

    public List<PagoResponse> listarTodos() {
        return pagoRepository.findAll().stream()
                .map(this::toResponse).toList();
    }

    public PagoResponse buscarPorId(Long id) {
        return toResponse(findOrThrow(id));
    }

    public PagoResponse buscarPorCita(Long citaId) {
        return pagoRepository.findByCitaId(citaId)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No existe pago para la cita con id: " + citaId));
    }

    public List<PagoResponse> listarPorEstado(String estado) {
        return pagoRepository.findByEstado(estado)
                .stream().map(this::toResponse).toList();
    }

    public PagoResponse cambiarEstado(Long id, String nuevoEstado) {
        List<String> estadosValidos = List.of("PENDIENTE", "PAGADO", "RECHAZADO");
        if (!estadosValidos.contains(nuevoEstado)) {
            throw new IllegalArgumentException("Estado inválido: " + nuevoEstado);
        }
        Pago pago = findOrThrow(id);
        pago.setEstado(nuevoEstado);
        return toResponse(pagoRepository.save(pago));
    }

    public boolean existePago(Long id) {
        return pagoRepository.existsById(id);
    }

    private Pago findOrThrow(Long id) {
        return pagoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Pago no encontrado con id: " + id));
    }

    private PagoResponse toResponse(Pago p) {
        return PagoResponse.builder()
                .id(p.getId())
                .montoTotal(p.getMontoTotal())
                .metodoPago(p.getMetodoPago())
                .estado(p.getEstado())
                .citaId(p.getCitaId())
                .build();
    }
}