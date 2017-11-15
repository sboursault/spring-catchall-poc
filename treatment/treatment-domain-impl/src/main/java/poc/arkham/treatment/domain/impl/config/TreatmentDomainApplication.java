package poc.arkham.treatment.domain.impl.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/*
 * Sprint boot appplication
 */
@SpringBootConfiguration
@EnableMongoRepositories(basePackages="poc.arkham.treatment.domain.impl.repository")
public class TreatmentDomainApplication {

}
