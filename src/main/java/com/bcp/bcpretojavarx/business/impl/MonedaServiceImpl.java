package com.bcp.bcpretojavarx.business.impl;

import com.bcp.bcpretojavarx.business.MonedaService;
import com.bcp.bcpretojavarx.controller.dto.MonedaResponse;
import com.bcp.bcpretojavarx.dao.repository.MonedaRepository;
import com.bcp.bcpretojavarx.exception.ResourceNotFoundException;
import com.bcp.bcpretojavarx.dao.entity.MonedaEntity;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MonedaServiceImpl implements MonedaService {
    @Autowired
    private MonedaRepository monedaRepository;
    @Autowired
    private  ModelMapper modelMapper;

    public Single<MonedaResponse> getMonedaId(final Long id) {
        return Single.create(singleSubscriber -> {
            final Optional<MonedaEntity> monedaEntity = this.monedaRepository.findById(id);
            if (monedaEntity.isPresent()) {
                singleSubscriber.onSuccess(modelMapper.map(monedaEntity.get(), MonedaResponse.class));
            } else {
                singleSubscriber.onError(new ResourceNotFoundException("Moneda not found"));
            }
        });
    }

    public Flowable<MonedaResponse> getMonedas() {
        return Flowable.fromIterable(this.monedaRepository.findAll())
                .map(moneda -> modelMapper.map(moneda, MonedaResponse.class));
    }
}
