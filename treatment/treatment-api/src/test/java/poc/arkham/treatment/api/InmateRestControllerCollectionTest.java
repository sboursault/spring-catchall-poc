
package poc.arkham.treatment.api;


import com.google.common.collect.Lists;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import poc.arkham.treatment.domain.impl.repository.InmateRepository;
import poc.arkham.treatment.domain.model.Aka;
import poc.arkham.treatment.domain.model.Inmate;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs(outputDir = "build/snippets/inmates")
public class InmateRestControllerCollectionTest extends AbstractRestControllerTest {

    @Autowired
    private InmateRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        repository.deleteAll();
    }

    @Test
    public void find_all() throws Exception {

        repository.save(InmateExamples.thePenguin().id("penguin").build());
        repository.save(InmateExamples.theJoker().id("joker").build());
        repository.save(InmateExamples.poisonIvy().id("poisonIvy").build());

        mockMvc.perform(
                        get("/inmates"))
                .andDo(print())
                .andExpect(
                        status().isOk())
                .andExpect(
                        jsonPath("$._embedded[*].id",
                                containsInAnyOrder("penguin", "joker", "poisonIvy")));
    }

    @Test
    public void find_partial() throws Exception {

        for (int i = 1; i <= 30; i++) {
            repository.save(Inmate.builder().id("inmate_" + i).firstname("unknown").lastname("unknown").build());
        }

        mockMvc.perform(
                get("/inmates?page=0&size=3&range=0-4"))
                .andDo(print())
                .andExpect(
                        status().isPartialContent())
                .andExpect(
                        jsonPath("$._embedded[*].id",
                                containsInAnyOrder("inmate_1", "inmate_2", "inmate_3", "inmate_4", "inmate_5")));
    }


}