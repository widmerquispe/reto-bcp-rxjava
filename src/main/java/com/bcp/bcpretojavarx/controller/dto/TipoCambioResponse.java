package com.bcp.bcpretojavarx.controller.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TipoCambioResponse
{
    private Long id;
    private String monedaNombre;
    private String monedaDestinoNombre;
    private BigDecimal tipoCambio;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
}
