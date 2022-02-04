package com.bcp.bcpretojavarx.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Table(name = "tipocambio")
@Entity
public class TipoCambioEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private MonedaEntity moneda;
    @ManyToOne
    private MonedaEntity destinoMoneda;

    private BigDecimal tipocambio;
    private LocalDate fechadesde;
    private LocalDate fechahasta;
}
