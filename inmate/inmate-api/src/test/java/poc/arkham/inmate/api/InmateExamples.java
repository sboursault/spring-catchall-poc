package poc.arkham.inmate.api;


import com.google.common.collect.Lists;
import poc.arkham.inmate.domain.model.Aka;
import poc.arkham.inmate.domain.model.Inmate;
import java.time.LocalDate;

public class InmateExamples {


    public static Inmate.InmateBuilder thePenguin() {
        return Inmate.builder()
                .id("penguin_1234")
                .firstname("Oswald")
                .lastname("Cobblepot")
                .birthDate(LocalDate.of(1960, 05, 31))
                .aka(Lists.newArrayList(Aka.builder().name("Penguin").build()));
    }

    public static Inmate.InmateBuilder theJoker() {
        return Inmate.builder()
                .id("joker_5555")
                .firstname("???")
                .lastname("???")
                .aka(Lists.newArrayList(Aka.builder().name("Joker").build()));
    }

    public static Inmate.InmateBuilder poisonIvy() {
        return Inmate.builder()
                .id("poisonIvy_7777")
                .firstname("Pamela")
                .lastname("Isley")
                .aka(Lists.newArrayList(Aka.builder().name("Poison Ivy").build()));
    }
}
