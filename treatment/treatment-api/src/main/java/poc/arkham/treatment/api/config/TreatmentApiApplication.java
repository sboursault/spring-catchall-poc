package poc.arkham.treatment.api.config;

import com.google.common.collect.Lists;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import poc.arkham.treatment.domain.service.InmateService;
import poc.arkham.treatment.domain.model.Aka;
import poc.arkham.treatment.domain.model.Inmate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

/*
 * Sprint boot appplication
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"poc.arkham.common", "poc.arkham.treatment"})
public class TreatmentApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TreatmentApiApplication.class, args);
    }


    @Bean
    CommandLineRunner init(InmateService inmateService) {
        // load basic inmates in not existing
        return args -> {
            inmateService.register(Inmate.builder()
                    .id("penguin_1234")
                    .firstname("Oswald")
                    .lastname("Cobblepot")
                    .birthDate(LocalDate.of(1960, 05, 31))
                    .aka(Lists.newArrayList(Aka.builder().name("Penguin").build()))
                    .build());
            inmateService.register(Inmate.builder()
                    .id("joker_5555")
                    .firstname("???")
                    .lastname("???")
                    .aka(Lists.newArrayList(Aka.builder().name("Joker").build()))
                    .build());
            inmateService.register(Inmate.builder()
                    .id("poisonIvy_7777")
                    .firstname("Pamela")
                    .lastname("Isley")
                    .aka(Lists.newArrayList(Aka.builder().name("Poison Ivy").build()))
                    .build());
        };
    }

}
