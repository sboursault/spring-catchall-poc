package poc.arkham.logistic.domain.impl.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/*
 * Sprint boot appplication
 */
@SpringBootApplication
@ComponentScan({"poc.arkham.logistic"})
@EnableMongoRepositories(basePackages="poc.arkham.logistic.domain.impl.repository")
public class LogisticDomainApplication {

}
