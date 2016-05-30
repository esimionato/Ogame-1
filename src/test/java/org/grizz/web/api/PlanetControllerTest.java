package org.grizz.web.api;

import org.grizz.model.Planet;
import org.grizz.service.PlanetService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class PlanetControllerTest {
    private static String ID = "some_id";

    private MockMvc mockMvc;

    @Mock
    private PlanetService planetService;

    @InjectMocks
    private PlanetController controller = new PlanetController();

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        when(planetService.get(ID)).thenReturn(dummyPlanet(ID));
    }

    @Test
    public void shouldReturnPlanetWhenGivenProperID() throws Exception {
        mockMvc.perform(get("/planets/" + ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(ID));
    }

    private Planet dummyPlanet(String id) {
        return Planet.builder().id(id).build();
    }


}