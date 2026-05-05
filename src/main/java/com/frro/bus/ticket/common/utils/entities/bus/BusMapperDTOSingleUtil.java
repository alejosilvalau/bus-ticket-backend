package com.frro.bus.ticket.common.utils.entities.bus;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.frro.bus.ticket.common.utils.EntityMapperDTOUtil;
import com.frro.bus.ticket.features.fleet.dtos.bus.BusDTO;
import com.frro.bus.ticket.features.fleet.entities.Bus;
import com.frro.bus.ticket.features.fleet.mappers.BusMapper;

@Mapper(componentModel = "spring")
public abstract class BusMapperDTOSingleUtil extends EntityMapperDTOUtil {

    @Autowired
    protected BusMapper busMapper;

    @Named("busToBusDTO")
    public BusDTO busToBusDTO(Bus bus) {
        return mapSingle(bus, busMapper::toBusDTO);
    }
}
