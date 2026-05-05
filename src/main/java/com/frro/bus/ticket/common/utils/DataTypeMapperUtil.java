package com.frro.bus.ticket.common.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

@Mapper(componentModel = "spring")
public class DataTypeMapperUtil {

    @Named("unwrapOptionalString")
    public String unwrapString(Optional<String> opt) {
        return opt.orElse(null);
    }

    @Named("unwrapOptionalBoolean")
    public Boolean unwrapBoolean(Optional<Boolean> opt) {
        return opt.orElse(null);
    }

    @Named("unwrapOptionalBigDecimal")
    public BigDecimal unwrapBigDecimal(Optional<BigDecimal> opt) {
        return opt.orElse(null);
    }

    @Named("unwrapOptionalZonedDateTime")
    public ZonedDateTime unwrapZonedDateTime(Optional<ZonedDateTime> opt) {
        return opt.orElse(null);
    }

    @Named("unwrapOptionalInteger")
    public Integer unwrapInt(Optional<Integer> opt) {
        return opt.orElse(null);
    }

    @Named("unwrapOptionalChararacter")
    public Character unwrapChar(Optional<Character> opt) {
        return opt.orElse(null);
    }
}
