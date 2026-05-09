package com.frro.bus.ticket.features.fleet.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.frro.bus.ticket.common.utils.DataTypeMapperUtils;
import com.frro.bus.ticket.common.utils.entities.EntityMapperUtils;
import com.frro.bus.ticket.common.utils.entities.bus.BusMapperDTOSingleUtil;
import com.frro.bus.ticket.common.utils.entities.seattype.SeatTypeMapperDTOSingleUtil;
import com.frro.bus.ticket.features.fleet.dtos.seat.SeatDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.SeatFullDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.CreateSeatDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.SearchSeatDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.UpdateSeatDTO;
import com.frro.bus.ticket.features.fleet.entities.Seat;

@Mapper(componentModel = "spring", uses = { DataTypeMapperUtils.class, EntityMapperUtils.class,
        BusMapperDTOSingleUtil.class,
        SeatTypeMapperDTOSingleUtil.class })
public interface SeatMapper {
    @Mapping(target = "isActive", source = "active")
    SeatDTO toSeatDTO(Seat seat);

    @Mapping(target = "isActive", source = "active")
    @Mapping(target = "bus", source = "bus", qualifiedByName = "busToBusDTO")
    @Mapping(target = "seatType", source = "seatType", qualifiedByName = "seatTypeToSeatTypeDTO")
    SeatFullDTO toSeatFullDTO(Seat seat);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", source = "isActive")
    @Mapping(target = "bus", source = "idBus", qualifiedByName = "idToBus")
    @Mapping(target = "seatType", source = "idSeatType", qualifiedByName = "idToSeatType")
    @Mapping(target = "tickets", ignore = true)
    Seat toSeat(CreateSeatDTO createSeatDto);

    @Mapping(target = "letter", source = "letter", qualifiedByName = "unwrapOptionalCharacter")
    @Mapping(target = "number", source = "number", qualifiedByName = "unwrapOptionalInteger")
    @Mapping(target = "active", source = "isActive", qualifiedByName = "unwrapOptionalBoolean")
    @Mapping(target = "bus", source = "idBus", qualifiedByName = "optionalIdToBus")
    @Mapping(target = "seatType", source = "idSeatType", qualifiedByName = "optionalIdToSeatType")
    @Mapping(target = "tickets", ignore = true)
    Seat toSeat(UpdateSeatDTO updateSeatDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "letter", source = "letter", qualifiedByName = "unwrapOptionalCharacter")
    @Mapping(target = "number", source = "number", qualifiedByName = "unwrapOptionalInteger")
    @Mapping(target = "active", source = "isActive", qualifiedByName = "unwrapOptionalBoolean")
    @Mapping(target = "bus", source = "idBus", qualifiedByName = "optionalIdToBus")
    @Mapping(target = "seatType", source = "idSeatType", qualifiedByName = "optionalIdToSeatType")
    @Mapping(target = "tickets", ignore = true)
    Seat toSeat(SearchSeatDTO searchSeatDto);
}
