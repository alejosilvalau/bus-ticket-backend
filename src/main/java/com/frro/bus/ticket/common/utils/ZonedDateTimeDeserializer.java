package com.frro.bus.ticket.common.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

        try {
            // Try parsing as ZonedDateTime (with timezone offset)
            ZonedDateTime parsed = ZonedDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
            return parsed.withZoneSameInstant(ZoneId.of("UTC"));
        } catch (DateTimeParseException e) {
            // If no timezone offset, parse as LocalDateTime and assume UTC
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
                return localDateTime.atZone(ZoneId.of("UTC"));
            } catch (DateTimeParseException e2) {
                // Fallback: try ISO_LOCAL_DATE_TIME format
                LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                return localDateTime.atZone(ZoneId.of("UTC"));
            }
        }
    }

}
