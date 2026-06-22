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
                String fieldName = p.currentName();
                String value = p.getValueAsString();

                if (value == null) {
                    return null;
                }

                if (fieldName != null && fieldName.toLowerCase().contains("password")) {
                    return value;
                }

                return value.trim().toLowerCase();
            }
        });
    }
}
