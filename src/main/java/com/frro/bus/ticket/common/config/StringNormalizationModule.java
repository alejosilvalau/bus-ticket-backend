package com.frro.bus.ticket.common.config;

import org.springframework.context.annotation.Configuration;

import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.deser.std.StdDeserializer;
import tools.jackson.databind.module.SimpleModule;

@Configuration
public class StringNormalizationModule extends SimpleModule {
    public StringNormalizationModule() {
        addDeserializer(String.class, new StdDeserializer<String>(String.class) {
            @Override
            public String deserialize(JsonParser p, DeserializationContext ctxt) {
                String value = p.getValueAsString();
                return value != null ? value.trim().toLowerCase() : null;
            }
        });
    }
}
