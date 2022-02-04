package com.bcp.bcpretojavarx.controller;

import com.bcp.bcpretojavarx.business.TipoCambioService;
import com.bcp.bcpretojavarx.business.MonedaService;
import com.bcp.bcpretojavarx.controller.dto.*;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/retojavarx")
public class RetojavarxController
{
    @Autowired
    private TipoCambioService tipoCambioService;

    @Autowired
    private MonedaService monedaService;

    public RetojavarxController() {
    }

    //Consultar moneda por identificador
    @GetMapping("/moneda/{id}")
    public Single<MonedaResponse> getMonedaId(@PathVariable final Long id) {
        return monedaService.getMonedaId(id);
    }

    //Listar todas las monedas
    @GetMapping("/listarMonedas")
    public Flowable<MonedaResponse> getMonedas() {
        return monedaService.getMonedas();
    }

    //Listar los tipos de cambios
    @GetMapping("/listarTipocambios")
    public Flowable<TipoCambioResponse> getTipoCambios() {
        return tipoCambioService.getTipoCambios();
    }

    //consultar tipo de cambio para un periodo especifico
    @GetMapping("/tipocambioxperiodo")
    public Single<TipoCambioResponse> getTipoCambioxMonedasAndPeriodo(@RequestBody final TipoCambioCalculoRequest TipoCambioCalculoRequest) {
        return tipoCambioService.getTipoCambioResponsePeriodo(TipoCambioCalculoRequest);
    }

    //La API debe recibir el valor “monto“, “moneda origen”, “moneda destino“ y devolver el “monto”, “monto con
    //tipo de cambio”, “moneda origen”, “moneda destino“ y “tipo de cambio”.
    @PostMapping("/transfTipoCambio")
    public Single<TipoCambioCalculoResponse> calcularMontoTransf(@RequestBody final TipoCambioCalculoRequest tipoCambioCalculoRequest) {
        return tipoCambioService.calculoTipoCambioMonto(tipoCambioCalculoRequest);
    }

    //Crear un POST para insertar el valor del tipo de cambio.
    @PostMapping("/insertarTipoCambio")
    public Single<TipoCambioResponse> creacionTipoCambio(@RequestBody final TipoCambioRequest tipoCambioRequest) {
        return tipoCambioService.creacionTipoCambio(tipoCambioRequest);
    }

    //Actualizar Tipo de Cambio
    @PutMapping(value = "/actualizarTipocambio/{id}")
    public Single<TipoCambioResponse> actualizarTipoCambio(@PathVariable(value = "id") final Long id,
                                                           @RequestBody TipoCambioRequest tipoCambioRequest) {
        return tipoCambioService.actualizarTipoCambio(id,tipoCambioRequest);
    }
}
