package com.frro.bus.ticket.common.utils.entities.seattype;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.frro.bus.ticket.common.utils.EntityMapperDTOUtil;
import com.frro.bus.ticket.features.fleet.dtos.seattype.SeatTypeDTO;
import com.frro.bus.ticket.features.fleet.entities.SeatType;
import com.frro.bus.ticket.features.fleet.mappers.SeatTypeMapper;

@Mapper(componentModel = "spring")
public class SeatTypeMapperDTOListUtil extends EntityMapperDTOUtil {

    @Autowired
    protected SeatTypeMapper seatTypeMapper;

    @Named("seatTypesToSeatTypeDTOs")
    public List<SeatTypeDTO> seatTypesToSeatTypeDTOs(List<SeatType> seatTypes) {
        return mapList(seatTypes, seatTypeMapper::toSeatTypeDTO);
    }
}