package com.frro.bus.ticket.common.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.deser.std.StdDeserializer;

public class ZonedDateTimeDeserializer extends StdDeserializer<ZonedDateTime> {

    public ZonedDateTimeDeserializer() {
        super(ZonedDateTime.class);
    }

    @Override
    public ZonedDateTime deserialize(JsonParser p, DeserializationContext ctxt) {
        String date = p.getValueAsString();
        // Try parse with timezone, fallback to UTC
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME
                .withZone(ZoneId.of("UTC"));
        return ZonedDateTime.parse(date, formatter);
    }

}
