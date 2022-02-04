package com.bcp.bcpretojavarx.controller.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TipoCambioRequest
{
    private Long monedaId;
    private Long destinoMonedaId;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private BigDecimal tipoCambio;
}
