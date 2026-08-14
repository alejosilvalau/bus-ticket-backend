package com.frro.bus.ticket;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.frro.bus.ticket.common.config.StringNormalizationModule;
import com.frro.bus.ticket.features.identity.dtos.driver.CreateDriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.UpdateDriverDTO;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

class LicenseNumberDeserializerTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = JsonMapper.builder()
                .addModule(new StringNormalizationModule())
                .build();
    }

    @Test
    void createDriverDTO_normalizesLicenseNumber() throws Exception {
        String json = """
                {"firstName":"Juan","lastName":"Perez","isActive":true,"licenseNumber":"30.123.456","phoneNumber":"+5491155551234"}
                """;

        CreateDriverDTO dto = objectMapper.readValue(json, CreateDriverDTO.class);

        assertThat(dto.licenseNumber()).isEqualTo("30123456");
    }

    @Test
    void createDriverDTO_stripsSpacesAndHyphens() throws Exception {
        String json = """
                {"firstName":"Juan","lastName":"Perez","isActive":true,"licenseNumber":"30 123-456","phoneNumber":"+5491155551234"}
                """;

        CreateDriverDTO dto = objectMapper.readValue(json, CreateDriverDTO.class);

        assertThat(dto.licenseNumber()).isEqualTo("30123456");
    }

    @Test
    void updateDriverDTO_normalizesLicenseNumberInsideOptional() throws Exception {
        String json = """
                {"id":1,"licenseNumber":"30.123.456"}
                """;

        UpdateDriverDTO dto = objectMapper.readValue(json, UpdateDriverDTO.class);

        assertThat(dto.licenseNumber()).hasValue("30123456");
    }
}
