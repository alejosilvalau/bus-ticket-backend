package com.frro.bus.ticket.features.fleet.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.frro.bus.ticket.common.utils.DataTypeMapperUtils;
import com.frro.bus.ticket.features.fleet.dtos.bus.BusDTO;
import com.frro.bus.ticket.features.fleet.dtos.bus.CreateBusDTO;
import com.frro.bus.ticket.features.fleet.dtos.bus.UpdateBusDTO;
import com.frro.bus.ticket.features.fleet.entities.Bus;

@Mapper(componentModel = "spring", uses = { DataTypeMapperUtils.class })
public interface BusMapper {
    @Mapping(target = "isActive", source = "active")
    BusDTO toBusDTO(Bus bus);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", source = "isActive")
    @Mapping(target = "seats", ignore = true)
    @Mapping(target = "trips", ignore = true)
    Bus toBus(CreateBusDTO createBusDto);

    @Mapping(target = "plateNumber", source = "plateNumber", qualifiedByName = "unwrapOptionalString")
    @Mapping(target = "totalCapacity", source = "totalCapacity", qualifiedByName = "unwrapOptionalInteger")
    @Mapping(target = "active", source = "isActive", qualifiedByName = "unwrapOptionalBoolean")
    @Mapping(target = "seats", ignore = true)
    @Mapping(target = "trips", ignore = true)
    Bus toBus(UpdateBusDTO updateBusDto);
}
