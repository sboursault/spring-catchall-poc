package poc.arkham.research.api.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import poc.arckham.research.domain.model.Medicine;
import poc.arckham.research.domain.service.MedicineService;
import poc.arkham.common.apiserver.config.SwaggerConfig;

/*
 * Sprint boot appplication
 */
@SpringBootApplication
@EnableDiscoveryClient
@Import(SwaggerConfig.class)
@ComponentScan({"poc.arkham.common.web", "poc.arkham.research"}) // TODO: shouldn't need to scan poc.arkham.common
public class ResearchApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResearchApiApplication.class, args);
    }


    @Bean
    CommandLineRunner init(MedicineService medicineService) {
        // generates a medicine set if not existing

        // test with have: 3 results
        // then test with have + mornin : still 3 results but DD comes first

        // TODO add more data to demonstrate hateoas links on collections

        return args -> {
            medicineService.register(Medicine.newMedicine()
                    .id("1")
                    .label("AAA BBB")
                    .description("Heard joke once: Man goes to doctor. Says he's depressed. Says life seems harsh and cruel. Says he feels all alone in a threatening world where what lies ahead is vague and uncertain. Doctor says, \"Treatment is simple. Great clown Pagliacci is in town tonight. Go and see him. That should pick you up.\" Man bursts into tears. Says, \"But doctor...I am Pagliacci.")
                    .build());
            medicineService.register(Medicine.newMedicine()
                    .id("2")
                    .label("ABB BBB")
                    .description("Stood in firelight, sweltering. Bloodstain on chest like map of violent new continent. Felt cleansed. Felt dark planet turn under my feet and knew what cats know that makes them scream like babies in night.\n" +
                            "Looked at sky through smoke heavy with human fat and God was not there. The cold, suffocating dark goes on forever and we are alone. Live our lives, lacking anything better to do. Devise reason later. Born from oblivion; bear children, hell-bound as ourselves, go into oblivion. There is nothing else.\n" +
                            "Existence is random. Has no pattern save what we imagine after staring at it for too long. No meaning save what we choose to impose. This rudderless world is not shaped by vague metaphysical forces. It is not God who kills the children. Not fate that butchers them or destiny that feeds them to the dogs. It’s us. Only us. Streets stank of fire. The void breathed hard on my heart, turning its illusions to ice, shattering them. Was reborn then, free to scrawl own design on this morally blank world.\n" +
                            "Was Rorschach.\n" +
                            "Does that answer your Questions, Doctor?")
                    .build());
            medicineService.register(Medicine.newMedicine()
                    .id("3")
                    .label("BBBB DDD")
                    .description("Thermodynamic miracles... events with odds against so astronomical they're effectively impossible, like oxygen spontaneously becoming gold. I long to observe such a thing.\n" +
                            "And yet, in each human coupling, a thousand million sperm vie for a single egg. Multiply those odds by countless generations, against the odds of your ancestors being alive; meeting; siring this precise son; that exact daughter... Until your mother loves a man she has every reason to hate, and of that union, of the thousand million children competing for fertilization, it was you, only you, that emerged. To distill so specific a form from that chaos of improbability, like turning air to gold... that is the crowning unlikelihood. The thermodynamic miracle.\n" +
                            "But...if me, my birth, if that's a thermodynamic miracle... I mean, you could say that about anybody in the world!.\n" +
                            "Yes. Anybody in the world. ..But the world is so full of people, so crowded with these miracles that they become commonplace and we forget... I forget. We gaze continually at the world and it grows dull in our perceptions. Yet seen from the another's vantage point. As if new, it may still take our breath away. Come...dry your eyes. For you are life, rarer than a quark and unpredictable beyond the dreams of Heisenberg; the clay in which the forces that shape all things leave their fingerprints most clearly. Dry your eyes... and let's go home.")
                    .build());
            medicineService.register(Medicine.newMedicine()
                    .id("4")
                    .label("DD")
                    .description("Roschach's Journal: October 12th, 1985\n" +
                            "Dog carcass in alley this morning, tire tread on burst stomach. This city is afraid of me. I have seen its true face.\n" +
                            "The streets are extended gutters and the gutters are full of blood and when the drains finally scab over, all the vermin will drown.\n" +
                            "The accumulated filth of all their sex and murder will foam up about their waists and all the whores and politicians will look up and shout \"Save us!\"... and I'll look down and whisper \"No.”")
                    .build());
            medicineService.register(Medicine.newMedicine()
                    .id("5")
                    .label("AAA BBB CCC")
                    .description("It's funny, but certain faces seem to go in and out of style. You look at old photographs and everybody has a certain look to them, almost as if they're related. Look at pictures from ten years later and you can see that there's a new kind of face starting to predominate, and that the old faces are fading away and vanishing, never to be seen again.").build());

        };
    }

}
