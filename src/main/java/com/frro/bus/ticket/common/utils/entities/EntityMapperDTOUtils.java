package com.frro.bus.ticket.common.utils.entities;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class EntityMapperDTOUtils {
    protected <T, D> D mapSingle(T entity, java.util.function.Function<T, D> mapper) {
        return entity != null ? mapper.apply(entity) : null;
    }

    protected <T, D> List<D> mapList(List<T> entities, java.util.function.Function<T, D> mapper) {
        return entities != null ? entities.stream().map(mapper).toList() : List.of();
    }
}
