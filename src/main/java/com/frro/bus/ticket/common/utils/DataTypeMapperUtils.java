package com.frro.bus.ticket.common.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class DataTypeMapperUtils {

    @Named("unwrapOptionalString")
    public String unwrapOptionalString(Optional<String> opt) {
        return opt.orElse(null);
    }

    @Named("unwrapOptionalBoolean")
    public Boolean unwrapOptionalBoolean(Optional<Boolean> opt) {
        return opt.orElse(null);
    }

    @Named("unwrapOptionalBigDecimal")
    public BigDecimal unwrapOptionalBigDecimal(Optional<BigDecimal> opt) {
        return opt.orElse(null);
    }

    @Named("unwrapOptionalZonedDateTime")
    public ZonedDateTime unwrapOptionalZonedDateTime(Optional<ZonedDateTime> opt) {
        return opt.orElse(null);
    }

    @Named("unwrapOptionalInteger")
    public Integer unwrapOptionalInteger(Optional<Integer> opt) {
        return opt.orElse(null);
    }

    @Named("unwrapOptionalCharacter")
    public Character unwrapOptionalCharacter(Optional<Character> opt) {
        return opt.orElse(null);
    }
}
