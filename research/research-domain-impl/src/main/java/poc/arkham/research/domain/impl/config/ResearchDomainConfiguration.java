package poc.arkham.research.domain.impl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import poc.arckham.research.domain.service.MedicineEventPublisher;
import poc.arkham.research.domain.impl.service.DefaultMedicineService;

/*
 * Module configuration
 */
@Configuration
public class ResearchDomainConfiguration {

    @Bean
    DefaultMedicineService defaultMedicineService(MedicineEventPublisher medicineEventPublisher) {
        return new DefaultMedicineService(medicineEventPublisher);
    }

}
