package com.frro.bus.ticket.common.utils.entities.seat;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.frro.bus.ticket.common.utils.entities.EntityMapperDTOUtils;
import com.frro.bus.ticket.features.fleet.dtos.seat.SeatDTO;
import com.frro.bus.ticket.features.fleet.entities.Seat;
import com.frro.bus.ticket.features.fleet.mappers.SeatMapper;

@Mapper(componentModel = "spring")
public abstract class SeatMapperDTOSingleUtil extends EntityMapperDTOUtils {

    @Autowired
    protected SeatMapper seatMapper;

    @Named("seatToSeatDTO")
    public SeatDTO seatToSeatDTO(Seat seat) {
        return mapSingle(seat, seatMapper::toSeatDTO);
    }
}
