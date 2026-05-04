package com.frro.bus.ticket.common.utils;

import java.util.Optional;
import java.util.function.Supplier;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import com.frro.bus.ticket.features.fleet.entities.Bus;
import com.frro.bus.ticket.features.identity.entities.Driver;
import com.frro.bus.ticket.features.journey.entities.Location;

@Mapper(componentModel = "spring")
public abstract class EntityMapperUtil {
    
    // Generic helpers
    
    protected <T extends EntityWithId> Integer entityToId(T entity) {
        return entity != null ? entity.getId() : null;
    }
    
    protected <T extends EntityWithId> T idToEntity(Integer id, Supplier<T> constructor) {
        if (id == null) return null;
        T entity = constructor.get();
        entity.setId(id);
        return entity;
    }
    
    protected <T extends EntityWithId> T optionalIdToEntity(Optional<Integer> id, Supplier<T> constructor) {
        return id.map(idVal -> idToEntity(idVal, constructor)).orElse(null);
    }
    
    // Entity to id mappers
    
    @Named("busToId")
    public Integer busToId(Bus bus) {
        return entityToId(bus);
    }
    
    @Named("driverToId")
    public Integer driverToId(Driver driver) {
        return entityToId(driver);
    }
    
    @Named("locationToId")
    public Integer locationToId(Location location) {
        return entityToId(location);
    }
    
    // Id to entity mappers
    
    @Named("idToBus")
    public Bus idToBus(Integer busId) {
        return idToEntity(busId, Bus::new);
    }
    
    @Named("idToDriver")
    public Driver idToDriver(Integer driverId) {
        return idToEntity(driverId, Driver::new);
    }
    
    @Named("idToLocation")
    public Location idToLocation(Integer locationId) {
        return idToEntity(locationId, Location::new);
    }
    
    // Optional Id to entity mappers
    
    @Named("optionalIdToBus")
    public Bus idToBusOptional(Optional<Integer> idBus) {
        return optionalIdToEntity(idBus, Bus::new);
    }
    
    @Named("optionalIdToDriver")
    public Driver idToDriverOptional(Optional<Integer> idDriver) {
        return optionalIdToEntity(idDriver, Driver::new);
    }
    
    @Named("optionalIdToLocation")
    public Location idToLocationOptional(Optional<Integer> idLocation) {
        return optionalIdToEntity(idLocation, Location::new);
    }
}
