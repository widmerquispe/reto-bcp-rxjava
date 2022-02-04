package com.bcp.bcpretojavarx.business;

import com.bcp.bcpretojavarx.controller.dto.MonedaResponse;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface MonedaService {
    Single<MonedaResponse> getMonedaId(Long id);
    Flowable<MonedaResponse> getMonedas();
}
