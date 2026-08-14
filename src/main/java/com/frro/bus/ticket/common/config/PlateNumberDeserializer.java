package com.frro.bus.ticket.common.config;

import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.deser.std.StdDeserializer;

public class PlateNumberDeserializer extends StdDeserializer<String> {

    public PlateNumberDeserializer() {
        super(String.class);
    }

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) {
        String value = p.getValueAsString();
        if (value == null) {
            return null;
        }
        return value.trim().toLowerCase().replace(" ", "");
    }
}
