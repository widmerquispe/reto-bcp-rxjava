package com.bcp.bcpretojavarx.dao.repository;

import com.bcp.bcpretojavarx.dao.entity.MonedaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonedaRepository extends JpaRepository<MonedaEntity, Long>
{
}
