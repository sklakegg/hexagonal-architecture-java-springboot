package rebelsrescue.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import rebelsrescue.configuration.DomainConfiguration;

import java.util.Objects;

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

}
