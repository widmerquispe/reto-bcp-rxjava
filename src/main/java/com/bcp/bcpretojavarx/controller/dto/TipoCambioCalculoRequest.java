package com.bcp.bcpretojavarx.controller.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TipoCambioCalculoRequest
{
    private Long monedaId;
    private Long destinoMonedaId;
    private LocalDate periodo;
    private BigDecimal monto;
}
