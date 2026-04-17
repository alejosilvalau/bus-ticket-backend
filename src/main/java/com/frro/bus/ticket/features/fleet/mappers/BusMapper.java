package com.frro.bus.ticket.bus.mapper;

import com.frro.bus.ticket.bus.dto.BusRequest;
import com.frro.bus.ticket.bus.dto.BusResponse;
import com.frro.bus.ticket.bus.model.Bus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BusMapper {
    BusResponse toBusResponse(Bus bus);
    Bus toBus(BusRequest busRequest);
}
