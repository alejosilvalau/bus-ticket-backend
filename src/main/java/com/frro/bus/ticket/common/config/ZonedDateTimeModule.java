package com.frro.bus.ticket.common.config;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.context.annotation.Configuration;

import com.frro.bus.ticket.common.exceptions.InvalidDateFormatException;

import tools.jackson.core.JsonGenerator;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.deser.std.StdDeserializer;
import tools.jackson.databind.module.SimpleModule;
import tools.jackson.databind.ser.std.StdSerializer;

@Configuration
public class ZonedDateTimeModule extends SimpleModule {
    public ZonedDateTimeModule() {
        addDeserializer(ZonedDateTime.class, new StdDeserializer<ZonedDateTime>(ZonedDateTime.class) {
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
                            LocalDateTime localDateTime = LocalDateTime.parse(date,
                                    DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                            return localDateTime.atZone(ZoneId.of("UTC"));
                        } catch (DateTimeParseException e3) {
                            throw new InvalidDateFormatException(
                                    "Invalid date format: '" + date
                                            + "'. Expected formats: yyyy-MM-ddTHH:mm:ssZ, yyyy-MM-ddTHH:mm:ss+HH:mm, or yyyy-MM-ddTHH:mm:ss");
                        }
                    }
                }
            }
        });

        addSerializer(ZonedDateTime.class, new StdSerializer<ZonedDateTime>(ZonedDateTime.class) {
            @Override
            public void serialize(ZonedDateTime value, JsonGenerator gen, SerializationContext provider) {
                gen.writeString(value.format(DateTimeFormatter.ISO_INSTANT));
            }
        });
    }
}
