package poc.arkham.frontoffice.config;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("poc.arkham.frontoffice")
public class FrontOfficeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontOfficeApplication.class, args);
    }

}