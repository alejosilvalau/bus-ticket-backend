package com.frro.bus.ticket.common.utils.entities;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.frro.bus.ticket.common.utils.EntityMapperDTOUtil;
import com.frro.bus.ticket.features.fleet.dtos.seattype.SeatTypeDTO;
import com.frro.bus.ticket.features.fleet.entities.SeatType;
import com.frro.bus.ticket.features.fleet.mappers.SeatTypeMapper;

@Mapper(componentModel = "spring")
public abstract class SeatTypeMapperDTOSingleUtil extends EntityMapperDTOUtil {

    @Autowired
    protected SeatTypeMapper seatTypeMapper;

    @Named("seatTypeToSeatTypeDTO")
    public SeatTypeDTO seatTypeToSeatTypeDTO(SeatType seatType) {
        return mapSingle(seatType, seatTypeMapper::toSeatTypeDTO);
    }
}
