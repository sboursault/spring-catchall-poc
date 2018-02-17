
package poc.arkham.research.api;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import poc.arkham.research.api.config.ResearchApiApplication;
import poc.arkham.research.domain.impl.repository.MedicineRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static poc.arckham.research.domain.model.Medicine.newMedicine;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {ResearchApiApplication.class })
@WebAppConfiguration
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class MedicineRestControllerCollectionTest {

    @Autowired
    private MedicineRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        repository.deleteAll();
        repository.save(newMedicine().id("1").label("med aaaa").build());
        repository.save(newMedicine().id("2").label("med aaab").build());
        repository.save(newMedicine().id("3").label("med aaac").build());
        repository.save(newMedicine().id("4").label("med bbbb").build());
        repository.save(newMedicine().id("5").label("med cccc").build());
        repository.save(newMedicine().id("6").label("med ddee").build());
        repository.save(newMedicine().id("7").label("med eeee").build());
        repository.save(newMedicine().id("8").label("med fff_1").build());
        repository.save(newMedicine().id("9").label("med fff_2").build());
        repository.save(newMedicine().id("10").label("med fff_3").build());
        repository.save(newMedicine().id("11").label("med xxx").build());
        repository.save(newMedicine().id("12").label("med xxyz").build());
        repository.save(newMedicine().id("13").label("med xxzz").build());
        repository.save(newMedicine().id("14").label("med yyy").build());
    }

    @Test
    public void find_all() throws Exception {

        // when
        mockMvc.perform(
                get("/v1/medicines"))
                .andDo(print())
                .andExpect(
                        status().isPartialContent())
                .andExpect(
                        header().string("Content-Range", "0-4/5"))
                .andExpect(
                        header().string("Accept-Range", "medicine 25"))
                .andExpect(
                        content().json("{" +
                                "  \"_links\": {" +
                                "    \"next\": { \"href\": \"http://localhost/v1/medicines?page=1\" }," +
                                "    \"last\": { \"href\": \"http://localhost/v1/medicines?page=3\" }" +
                                "  }," +
                                "  \"_embedded\": [" +
                                "    {" +
                                "      \"label\": \"med fff_1\"," +
                                "      \"links\": { \"self\": { \"href\": \"http://localhost/v1/medicines/8\" } }" +
                                "    }," +
                                "    {" +
                                "      \"label\": \"med fff_2\"," +
                                "      \"links\": { \"self\": { \"href\": \"http://localhost/v1/medicines/9\" } }" +
                                "    }," +
                                "    {" +
                                "      \"label\": \"med fff_3\"," +
                                "      \"links\": { \"self\": { \"href\": \"http://localhost/v1/medicines/10\" } }" +
                                "    }," +
                                "    {" +
                                "      \"label\": \"med aaaa\"," +
                                "      \"links\": { \"self\": { \"href\": \"http://localhost/v1/medicines/1\" } }" +
                                "    }," +
                                "    {" +
                                "      \"label\": \"med aaab\"," +
                                "      \"links\": { \"self\": { \"href\": \"http://localhost/v1/medicines/2\" } }" +
                                "    }" +
                                "  ]" +
                                "}", false));

        // TODO add total number of results

    }
/*
    @Test
    public void find_partial() throws Exception {
        // TODO
    }

    @Test
    public void find_partial_end() throws Exception {

       // TODO
    }

    @Test
    public void find_too_big_range() throws Exception {

       // TODO
    }

    @Test
    public void find_malformed_range() throws Exception {

       // TODO
    }*/


}