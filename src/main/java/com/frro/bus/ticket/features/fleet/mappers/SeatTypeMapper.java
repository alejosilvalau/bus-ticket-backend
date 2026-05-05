package com.frro.bus.ticket.features.fleet.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.frro.bus.ticket.common.utils.DataTypeMapperUtil;
import com.frro.bus.ticket.features.fleet.dtos.seattype.SeatTypeDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.CreateSeatTypeDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.UpdateSeatTypeDTO;
import com.frro.bus.ticket.features.fleet.entities.SeatType;

@Mapper(componentModel = "spring", uses = DataTypeMapperUtil.class)
public interface SeatTypeMapper {
    SeatTypeDTO toSeatTypeDTO(SeatType seatType);

    @Mapping(target = "id", ignore = true)
    SeatType toSeatType(CreateSeatTypeDTO createSeatTypeDto);

    @Mapping(target = "name", source = "name", qualifiedByName = "unwrapOptionalString")
    @Mapping(target = "upcharge", source = "upcharge", qualifiedByName = "unwrapOptionalBigDecimal")
    SeatType toSeatType(UpdateSeatTypeDTO updateSeatTypeDto);
}
