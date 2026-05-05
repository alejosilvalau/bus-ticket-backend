package com.frro.bus.ticket.common.utils.entities.seat;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.frro.bus.ticket.common.utils.entities.EntityMapperDTOUtil;
import com.frro.bus.ticket.features.fleet.dtos.seat.SeatDTO;
import com.frro.bus.ticket.features.fleet.entities.Seat;
import com.frro.bus.ticket.features.fleet.mappers.SeatMapper;

@Mapper(componentModel = "spring")
public class SeatMapperDTOListUtil extends EntityMapperDTOUtil {

    @Autowired
    protected SeatMapper seatMapper;

    @Named("seatsToSeatDTOs")
    public List<SeatDTO> seatsToSeatDTOs(List<Seat> seats) {
        return mapList(seats, seatMapper::toSeatDTO);
    }
}