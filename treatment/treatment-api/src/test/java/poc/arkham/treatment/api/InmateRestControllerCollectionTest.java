
package poc.arkham.treatment.api;


import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.test.web.servlet.MockMvc;
import poc.arkham.treatment.domain.impl.repository.InmateRepository;
import poc.arkham.treatment.domain.model.Inmate;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
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
                        get("/v1/inmates"))
                .andDo(print())
                .andExpect(
                        status().isOk())
                .andExpect(
                        header().string("Content-Range", "0-2/3"))
                .andExpect(
                        header().string("Accept-Range", "inmates 25"))
                .andExpect(
                        jsonPath("$._links.collection.href", is("http://localhost/v1/inmates")))
                .andExpect(
                        jsonPath("$._links.first").doesNotExist())
                .andExpect(
                        jsonPath("$._links.prev").doesNotExist())
                .andExpect(
                        jsonPath("$._links.next").doesNotExist())
                .andExpect(
                        jsonPath("$._links.last").doesNotExist())
                .andExpect(
                        jsonPath("$._embedded[*].id",
                                containsInAnyOrder("penguin", "joker", "poisonIvy")))
                .andExpect(
                        jsonPath("$._embedded[?(@.id == 'joker')]._links.self.href",
                                contains("http://localhost/v1/inmates/joker")));
    }

    @Test
    public void find_partial() throws Exception {

        for (int i = 0; i < 30; i++) {
            repository.save(Inmate.builder().id("inmate_" + i).firstname("unknown").lastname("unknown").build());
        }

        mockMvc.perform(
                get("/v1/inmates?range=3-6"))
                .andDo(print())
                .andExpect(
                        status().isPartialContent())
                .andExpect(
                        header().string("Content-Range", "3-6/4"))
                .andExpect(
                        header().string("Accept-Range", "inmates 25"))
                .andExpect(
                        jsonPath("$._links.collection.href", is("http://localhost/v1/inmates")))
                .andExpect(
                        jsonPath("$._links.first.href", is("http://localhost/v1/inmates?range=0-3")))
                .andExpect(
                        jsonPath("$._links.prev.href", is("http://localhost/v1/inmates?range=-1-2")))
                .andExpect(
                        jsonPath("$._links.next.href", is("http://localhost/v1/inmates?range=7-10")))
                .andExpect(
                        jsonPath("$._links.last.href", is("http://localhost/v1/inmates?range=30-33")))
                .andExpect(
                        jsonPath("$._embedded[*].id",
                                containsInAnyOrder("inmate_3", "inmate_4", "inmate_5", "inmate_6")))
                .andExpect(
                        jsonPath("$._links.start.href", is("http://localhost/")));
    }

    @Test
    public void find_partial_end() throws Exception {

        for (int i = 0; i < 30; i++) {
            repository.save(Inmate.builder().id("inmate_" + i).firstname("unknown").lastname("unknown").build());
        }

        mockMvc.perform(
                get("/v1/inmates?range=28-40"))
                .andDo(print())
                .andExpect(
                        status().isPartialContent())
                .andExpect(
                        header().string("Content-Range", "28-29/2"))
                .andExpect(
                        header().string("Accept-Range", "inmates 25"))
                .andExpect(
                        jsonPath("$._embedded[*].id",
                                containsInAnyOrder("inmate_28", "inmate_29")));
    }

    @Test
    public void find_too_big_range() throws Exception {

        for (int i = 0; i < 30; i++) {
            repository.save(Inmate.builder().id("inmate_" + i).firstname("unknown").lastname("unknown").build());
        }

        mockMvc.perform(
                get("/v1/inmates?range=0-25"))
                .andDo(print())
                .andExpect(
                        status().isBadRequest())
                .andExpect(
                        jsonPath("$.messages[0]", containsString(
                                "Requested range is too big")));
    }

    @Test
    public void find_malformed_range() throws Exception {

        for (int i = 0; i < 30; i++) {
            repository.save(Inmate.builder().id("inmate_" + i).firstname("unknown").lastname("unknown").build());
        }

        mockMvc.perform(
                get("/v1/inmates?range=0-oups"))
                .andDo(print())
                .andExpect(
                        status().isBadRequest())
                .andExpect(
                        jsonPath("$.messages[0]", containsString(
                                "range must match ^\\d+-\\d+$")));
    }
}