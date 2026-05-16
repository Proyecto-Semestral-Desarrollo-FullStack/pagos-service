package com.veterinaria.pagos.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagoResponse {
    private Long id;
    private Double montoTotal;
    private String metodoPago;
    private String estado;
    private Long citaId;
}