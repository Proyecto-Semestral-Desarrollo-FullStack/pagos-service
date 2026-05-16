package com.veterinaria.pagos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "pagos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Double montoTotal;

    @NotBlank
    @Column(nullable = false)
    private String metodoPago;

    @Column(nullable = false)
    private String estado;

    @NotNull
    @Column(nullable = false, unique = true)
    private Long citaId;
}