package com.frro.bus.ticket.common.utils.entities;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.frro.bus.ticket.common.utils.EntityMapperDTOUtil;
import com.frro.bus.ticket.features.fleet.dtos.bus.BusDTO;
import com.frro.bus.ticket.features.fleet.entities.Bus;
import com.frro.bus.ticket.features.fleet.mappers.BusMapper;

@Mapper(componentModel = "spring")
public abstract class BusMapperDTOListUtil extends EntityMapperDTOUtil {

    @Autowired
    protected BusMapper busMapper;

    @Named("busToBusDTOs")
    public List<BusDTO> busToBusDTOs(List<Bus> buses) {
        return mapList(buses, busMapper::toBusDTO);
    }
}