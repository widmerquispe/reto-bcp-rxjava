package com.bcp.bcpretojavarx.dao.repository;

import com.bcp.bcpretojavarx.dao.entity.TipoCambioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface TipoCambioRepository extends JpaRepository<TipoCambioEntity, Long>
{

    @Query("SELECT cr FROM TipoCambioEntity cr WHERE cr.moneda.id = :monedaId " +
           "AND cr.destinoMoneda.id = :destinoMonedaId " +
           "AND :periodo BETWEEN cr.fechadesde AND cr.fechahasta")
    Optional<TipoCambioEntity> findByMonedas(Long monedaId, Long destinoMonedaId, LocalDate periodo);
}
