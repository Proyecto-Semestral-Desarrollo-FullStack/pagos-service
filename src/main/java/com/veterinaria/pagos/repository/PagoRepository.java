package com.veterinaria.pagos.repository;

import com.veterinaria.pagos.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PagoRepository extends JpaRepository<Pago, Long> {
    Optional<Pago> findByCitaId(Long citaId);
    boolean existsByCitaId(Long citaId);
    List<Pago> findByEstado(String estado);
    List<Pago> findByMetodoPago(String metodoPago);
}