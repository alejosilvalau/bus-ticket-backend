package com.frro.bus.ticket.features.fleet.mappers;

import com.frro.bus.ticket.features.fleet.dtos.SeatTypeDto;
import com.frro.bus.ticket.features.fleet.entities.SeatType;

public class SeatTypeMapper {
    public static SeatTypeDto toDto(SeatType seatType) {
        if (seatType == null) {
            return null;
        }
        return new SeatTypeDto(seatType.getId(), seatType.getName());
    }

    public static SeatType toEntity(SeatTypeDto seatTypeDto) {
        if (seatTypeDto == null) {
            return null;
        }
        SeatType seatType = new SeatType();
        seatType.setId(seatTypeDto.getId());
        seatType.setName(seatTypeDto.getName());
        return seatType;
    }
}
