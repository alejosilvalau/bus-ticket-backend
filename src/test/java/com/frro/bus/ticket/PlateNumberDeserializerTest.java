package com.frro.bus.ticket;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.frro.bus.ticket.common.config.StringNormalizationModule;
import com.frro.bus.ticket.features.fleet.dtos.bus.CreateBusDTO;
import com.frro.bus.ticket.features.fleet.dtos.bus.UpdateBusDTO;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

class PlateNumberDeserializerTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = JsonMapper.builder()
                .addModule(new StringNormalizationModule())
                .build();
    }

    @Test
    void createBusDTO_normalizesPlateNumber() throws Exception {
        String json = """
                {"plateNumber":" AB 123 CD ","totalCapacity":40,"isActive":true}
                """;

        CreateBusDTO dto = objectMapper.readValue(json, CreateBusDTO.class);

        assertThat(dto.plateNumber()).isEqualTo("ab123cd");
    }

    @Test
    void createBusDTO_normalizesOldFormat() throws Exception {
        String json = """
                {"plateNumber":"ABC 123","totalCapacity":40,"isActive":true}
                """;

        CreateBusDTO dto = objectMapper.readValue(json, CreateBusDTO.class);

        assertThat(dto.plateNumber()).isEqualTo("abc123");
    }

    @Test
    void createBusDTO_stripsHyphensAndDots() throws Exception {
        String json = """
                {"plateNumber":"AB-123-CD","totalCapacity":40,"isActive":true}
                """;

        CreateBusDTO dto = objectMapper.readValue(json, CreateBusDTO.class);

        assertThat(dto.plateNumber()).isEqualTo("ab123cd");
    }

    @Test
    void createBusDTO_stripsDots() throws Exception {
        String json = """
                {"plateNumber":"AB.123.CD","totalCapacity":40,"isActive":true}
                """;

        CreateBusDTO dto = objectMapper.readValue(json, CreateBusDTO.class);

        assertThat(dto.plateNumber()).isEqualTo("ab123cd");
    }

    @Test
    void updateBusDTO_normalizesPlateNumberInsideOptional() throws Exception {
        String json = """
                {"id":1,"plateNumber":"AB 123 CD"}
                """;

        UpdateBusDTO dto = objectMapper.readValue(json, UpdateBusDTO.class);

        assertThat(dto.plateNumber()).hasValue("ab123cd");
    }
}
