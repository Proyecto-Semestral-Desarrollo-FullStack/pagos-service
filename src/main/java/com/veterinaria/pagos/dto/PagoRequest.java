package com.veterinaria.pagos.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PagoRequest {

    @NotNull(message = "El monto total es obligatorio")
    @Positive(message = "El monto debe ser positivo")
    private Double montoTotal;

    @NotBlank(message = "El método de pago es obligatorio")
    @Pattern(regexp = "EFECTIVO|TARJETA|TRANSFERENCIA",
            message = "Método inválido. Use: EFECTIVO, TARJETA o TRANSFERENCIA")
    private String metodoPago;

    @NotNull(message = "El citaId es obligatorio")
    private Long citaId;
}