package com.frro.bus.ticket.common.config;

import org.springframework.context.annotation.Configuration;

import com.frro.bus.ticket.common.utils.ZonedDateTimeDeserializer;
import tools.jackson.databind.module.SimpleModule;

@Configuration
public class DateTimeModule extends SimpleModule {

    public DateTimeModule() {
        addDeserializer(java.time.ZonedDateTime.class, new ZonedDateTimeDeserializer());
    }

}
