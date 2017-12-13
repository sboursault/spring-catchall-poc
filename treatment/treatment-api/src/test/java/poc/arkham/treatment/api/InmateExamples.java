package poc.arkham.treatment.api;


import com.google.common.collect.Lists;
import poc.arkham.treatment.domain.model.Aka;
import poc.arkham.treatment.domain.model.Inmate;
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

    public static Inmate.InmateBuilder mrFreeze() {
        return Inmate.builder()
                .id("mrFreeze_888")
                .firstname("Victor")
                .lastname("Fries")
                .aka(Lists.newArrayList(Aka.builder().name("Mr. Freeze").build()));
    }

    public static Inmate.InmateBuilder madHatter() {
        return Inmate.builder()
                .id("madHatter_999")
                .firstname("Jervis")
                .lastname("Tetch")
                .aka(Lists.newArrayList(Aka.builder().name("Mad Hatter").build()));
    }

    public static Inmate.InmateBuilder scarecrow() {
        return Inmate.builder()
                .id("scarecrow_111")
                .firstname("Jonathan")
                .lastname("Crane")
                .aka(Lists.newArrayList(Aka.builder().name("Scarecrow").build()));
    }

}
