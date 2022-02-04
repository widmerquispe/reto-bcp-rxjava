package com.bcp.bcpretojavarx.config;

import com.bcp.bcpretojavarx.controller.dto.TipoCambioRequest;
import com.bcp.bcpretojavarx.dao.entity.TipoCambioEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration
{
    public static final String SECRET_KEY = "mySecretKey";

    @Bean
    public ModelMapper modelMapper()
    {
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.typeMap(TipoCambioRequest.class, TipoCambioEntity.class).addMappings(
            mapper -> {
                mapper.skip(TipoCambioRequest::getMonedaId, TipoCambioEntity::setId);
                mapper.skip(TipoCambioRequest::getDestinoMonedaId, TipoCambioEntity::setId);
            }
        );
        return modelMapper;
    }
}
