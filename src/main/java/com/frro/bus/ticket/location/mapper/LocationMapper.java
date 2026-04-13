package com.frro.bus.ticket.location.mapper;

import com.frro.bus.ticket.location.dto.LocationRequest;
import com.frro.bus.ticket.location.dto.LocationResponse;
import com.frro.bus.ticket.location.model.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationResponse toLocationResponse(Location location);
    Location toLocation(LocationRequest locationRequest);
}
