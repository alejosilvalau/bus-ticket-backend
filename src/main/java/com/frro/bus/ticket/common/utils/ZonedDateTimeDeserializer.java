package com.frro.bus.ticket.common.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.frro.bus.ticket.common.exceptions.InvalidDateFormatException;

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

        if (date == null || date.isBlank()) {
            return null;
        }

        try {
            ZonedDateTime parsed = ZonedDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
            return parsed.withZoneSameInstant(ZoneId.of("UTC"));
        } catch (DateTimeParseException e) {
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
                return localDateTime.atZone(ZoneId.of("UTC"));
            } catch (DateTimeParseException e2) {
                try {
                    LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    return localDateTime.atZone(ZoneId.of("UTC"));
                } catch (DateTimeParseException e3) {
                    throw new InvalidDateFormatException(
                            "Invalid date format: '" + date + "'. Expected formats: yyyy-MM-ddTHH:mm:ssZ, yyyy-MM-ddTHH:mm:ss+HH:mm, or yyyy-MM-ddTHH:mm:ss");
                }
            }
        }
    }
}
