package com.frro.bus.ticket.features.fleet.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.frro.bus.ticket.common.utils.OptionalMapperUtil;
import com.frro.bus.ticket.features.fleet.dtos.seat.SeatDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.CreateSeatDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.UpdateSeatDTO;
import com.frro.bus.ticket.features.fleet.entities.Seat;

@Mapper(componentModel = "spring", uses = OptionalMapperUtil.class)
public interface SeatMapper {
    SeatDTO toSeatDTO(Seat seat);

    @Mapping(target = "id", ignore = true)
    Seat toSeat(CreateSeatDTO createSeatDto);

    @Mapping(target = "letter", source = "letter", qualifiedByName = "unwrapOptionalChararacter")
    @Mapping(target = "number", source = "number", qualifiedByName = "unwrapOptionalInteger")
    Seat toSeat(UpdateSeatDTO updateSeatDto);
}
