
package poc.arkham.treatment.api;


import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import poc.arkham.treatment.domain.impl.repository.InmateRepository;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static poc.arkham.treatment.api.InmateExamples.thePenguin;


public class InmateRestControllerHateoasTest extends AbstractRestControllerTest {

    @Autowired
    private InmateRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        repository.deleteAll();
    }

    @Test
    public void start() throws Exception {
        mockMvc.perform(
                        get("/"))
                .andDo(
                        print())
                .andExpect(
                        status().isOk())
                .andExpect(
                        jsonPath("$._links.inmates.href", is("http://localhost/v1/inmates")));
    }

    @Test
    public void find_one() throws Exception {

        repository.save(thePenguin().id("penguin_1234").build());

        mockMvc.perform(
                        get("/v1/inmates/penguin_1234"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        jsonPath("$._links.self.href", is("http://localhost/v1/inmates/penguin_1234")))
                .andExpect(
                        jsonPath("$._links.collection.href", is("http://localhost/v1/inmates")))
                .andExpect(
                        jsonPath("$._links.start.href", is("http://localhost/")));
    }

    @Test
    public void post_returns_the_entity_location() throws Exception {
        MvcResult result = mockMvc.perform(
                        postJson("/v1/inmates").content("{\"firstname\": \"Harvey\", \"lastname\": \"Dent\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", not(isEmptyString())))
                .andReturn();

        String location = result.getResponse().getHeader("Location");

        mockMvc.perform(get(location))
                .andExpect(jsonPath("$.firstname", is("Harvey")));
    }


}