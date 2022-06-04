package rebelsrescue.controllers;

import ddd.DomainService;
import ddd.Stub;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import rebelsrescue.configuration.DomainConfiguration;
import rebelsrescue.fleet.Fleet;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RescueFleetController.class)
@Import(DomainConfiguration.class)
final class RescueFleetControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_assemble_a_rescue_fleet() throws Exception {
        mockMvc.perform(
                        post("/rescueFleets")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{ \"numberOfPassengers\" : 5 }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.starships").value(hasSize(1)))
                .andExpect(jsonPath("$.starships[0].name").value("Millennium Falcon"))
                .andExpect(jsonPath("$.starships[0].capacity").value("6"));
    }

    @TestConfiguration
    @ComponentScan(
            basePackageClasses = {Fleet.class},
            includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Stub.class})})
    static class StubConfiguration{}

}
