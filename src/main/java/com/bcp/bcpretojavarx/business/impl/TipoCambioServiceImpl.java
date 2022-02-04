package com.bcp.bcpretojavarx.business.impl;

import com.bcp.bcpretojavarx.business.TipoCambioService;
import com.bcp.bcpretojavarx.controller.dto.TipoCambioCalculoRequest;
import com.bcp.bcpretojavarx.controller.dto.TipoCambioCalculoResponse;
import com.bcp.bcpretojavarx.controller.dto.TipoCambioRequest;
import com.bcp.bcpretojavarx.controller.dto.TipoCambioResponse;
import com.bcp.bcpretojavarx.dao.repository.TipoCambioRepository;
import com.bcp.bcpretojavarx.dao.repository.MonedaRepository;
import com.bcp.bcpretojavarx.exception.ResourceNotFoundException;
import com.bcp.bcpretojavarx.dao.entity.MonedaEntity;
import com.bcp.bcpretojavarx.dao.entity.TipoCambioEntity;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipoCambioServiceImpl implements TipoCambioService {
    @Autowired
    private TipoCambioRepository tipoCambioRepository;
    @Autowired
    private MonedaRepository monedaRepository;
    @Autowired
    private  ModelMapper modelMapper;

    public Single<TipoCambioEntity> getTipoCambioxPeriodo(final TipoCambioCalculoRequest tipoCambioCalculoRequest) {
        return Single.create(singleSubscriber -> {
            final Optional<TipoCambioEntity> tipoCambioEntity = tipoCambioRepository.findByMonedas(
                    tipoCambioCalculoRequest.getMonedaId(),
                    tipoCambioCalculoRequest.getDestinoMonedaId(),
                    tipoCambioCalculoRequest.getPeriodo());
            if (tipoCambioEntity.isPresent()) {
                singleSubscriber.onSuccess(tipoCambioEntity.get());
            } else {
                singleSubscriber.onError(new ResourceNotFoundException("Tipo de cambio not found"));
            }
        });
    }

    public Single<TipoCambioResponse> getTipoCambioResponsePeriodo(final TipoCambioCalculoRequest tipoCambioCalculoRequest) {
        return getTipoCambioxPeriodo(tipoCambioCalculoRequest)
                .map(tipoCambio -> modelMapper.map(tipoCambio, TipoCambioResponse.class));
    }

    public Flowable<TipoCambioResponse> getTipoCambios() {
        return Flowable.fromIterable(tipoCambioRepository.findAll())
                .map(tipoCambio -> modelMapper.map(tipoCambio, TipoCambioResponse.class));
    }

    public Single<TipoCambioCalculoResponse> calculoTipoCambioMonto(final TipoCambioCalculoRequest tipoCambioCalculoRequest) {
        return getTipoCambioxPeriodo(tipoCambioCalculoRequest)
                .map(tipoCambio -> {
                    final TipoCambioCalculoResponse tipoCambioResponse =  modelMapper.map(
                            tipoCambio, TipoCambioCalculoResponse.class);
                    tipoCambioResponse.setMonto(tipoCambioCalculoRequest.getMonto());
                    tipoCambioResponse.setMontoTransf(tipoCambioCalculoRequest.getMonto().multiply(tipoCambio.getTipocambio()));
                    return tipoCambioResponse;
                });
    }

    public Single<TipoCambioResponse> creacionTipoCambio(final TipoCambioRequest tipoCambioRequest) {
        return Single.create(singleSubscriber -> {
            final TipoCambioEntity tipoCambioEntity = modelMapper.map(tipoCambioRequest, TipoCambioEntity.class);
            final Optional<MonedaEntity> monedaEntity = monedaRepository.findById(tipoCambioRequest.getMonedaId());
            if (monedaEntity.isPresent()) {
                tipoCambioEntity.setMoneda(monedaEntity.get());
            } else {
                singleSubscriber.onError(new ResourceNotFoundException("Moneda not found"));
            }
            final Optional<MonedaEntity> destinoMoneda = monedaRepository.findById(tipoCambioRequest.getDestinoMonedaId());
            if (destinoMoneda.isPresent()) {
                tipoCambioEntity.setDestinoMoneda(destinoMoneda.get());
            } else {
                singleSubscriber.onError(new ResourceNotFoundException("Moneda not found"));
            }
            TipoCambioEntity tipoCambioSv = tipoCambioRepository.save(tipoCambioEntity);
            singleSubscriber.onSuccess(modelMapper.map(tipoCambioSv, TipoCambioResponse.class));
        });
    }

    @Override
    public Single<TipoCambioResponse> actualizarTipoCambio(final Long id, TipoCambioRequest tipoCambioRequest) {
        return Single.create(singleSubscriber -> {
            final TipoCambioEntity tipoCambioEntity = modelMapper.map(tipoCambioRequest, TipoCambioEntity.class);
            final Optional<TipoCambioEntity> optionalTipoCambioEntity = tipoCambioRepository.findById(id);

            if (optionalTipoCambioEntity.isPresent()) {
                TipoCambioEntity preTipoCambioEntity = optionalTipoCambioEntity.get();
                tipoCambioEntity.setId(id);
                tipoCambioEntity.setMoneda(preTipoCambioEntity.getMoneda());
                tipoCambioEntity.setDestinoMoneda(preTipoCambioEntity.getDestinoMoneda());
                tipoCambioEntity.setFechadesde(preTipoCambioEntity.getFechadesde());
                tipoCambioEntity.setFechahasta(preTipoCambioEntity.getFechahasta());

            } else {
                singleSubscriber.onError(new ResourceNotFoundException("Tipo Cambio not found"));
            }
            TipoCambioEntity tipoCambioSv = tipoCambioRepository.save(tipoCambioEntity);
            singleSubscriber.onSuccess(modelMapper.map(tipoCambioSv, TipoCambioResponse.class));
        });
    }
}
