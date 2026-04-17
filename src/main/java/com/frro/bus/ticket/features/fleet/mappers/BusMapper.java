package com.frro.bus.ticket.features.fleet.mappers;

import org.mapstruct.Mapper;

import com.frro.bus.ticket.features.fleet.dtos.BusRequest;
import com.frro.bus.ticket.features.fleet.dtos.BusResponse;

@Mapper(componentModel = "spring")
public interface BusMapper {
    BusResponse toBusResponse(Bus bus);

    Bus toBus(BusRequest busRequest);
}
