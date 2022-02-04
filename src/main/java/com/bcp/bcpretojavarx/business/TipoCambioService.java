package com.bcp.bcpretojavarx.business;

import com.bcp.bcpretojavarx.controller.dto.TipoCambioCalculoRequest;
import com.bcp.bcpretojavarx.controller.dto.TipoCambioCalculoResponse;
import com.bcp.bcpretojavarx.controller.dto.TipoCambioRequest;
import com.bcp.bcpretojavarx.controller.dto.TipoCambioResponse;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface TipoCambioService {

    Flowable<TipoCambioResponse> getTipoCambios();
    Single<TipoCambioResponse> getTipoCambioResponsePeriodo(final TipoCambioCalculoRequest tipoCambioCalculoRequest);
    Single<TipoCambioCalculoResponse> calculoTipoCambioMonto(final TipoCambioCalculoRequest tipoCambioCalculoRequest);
    Single<TipoCambioResponse> creacionTipoCambio(final TipoCambioRequest tipoCambioRequest);
    Single<TipoCambioResponse> actualizarTipoCambio(final Long id, TipoCambioRequest tipoCambioRequest);
}
