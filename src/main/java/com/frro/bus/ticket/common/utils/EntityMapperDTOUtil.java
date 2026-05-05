package com.frro.bus.ticket.common.utils;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class EntityMapperDTOUtil {
    protected <T, D> D mapSingle(T entity, java.util.function.Function<T, D> mapper) {
        return entity != null ? mapper.apply(entity) : null;
    }

    protected <T, D> List<D> mapList(List<T> entities, java.util.function.Function<T, D> mapper) {
        return entities != null ? entities.stream().map(mapper).toList() : List.of();
    }
}
