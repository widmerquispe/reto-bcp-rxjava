package com.bcp.bcpretojavarx.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "moneda")
public class MonedaEntity
{
    @Id
    private Long id;
    private String nombre;
    private String simbolo;

    @OneToMany(mappedBy = "moneda")
    private List<TipoCambioEntity> tipoCambioEntities;
}
