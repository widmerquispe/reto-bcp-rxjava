package com.bcp.bcpretojavarx.controller.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TipoCambioCalculoResponse
{
    private String monedaNombre;
    private String monedaDestinoNombre;
    private BigDecimal monto;
    private BigDecimal tipoCambio;
    private BigDecimal montoTransf;
}
