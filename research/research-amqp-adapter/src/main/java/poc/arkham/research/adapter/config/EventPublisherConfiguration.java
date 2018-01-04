package poc.arkham.research.adapter.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import poc.arckham.research.domain.service.MedicineEventPublisher;
import poc.arkham.research.adapter.impl.AmqpMedicineEventPublisher;

/*
 * Module configuration
 */
@Configuration
public class EventPublisherConfiguration {

    @Value("${spring.rabitmq.medicine.exchange:medicine.exchange}")
    private String exchangeName;

    @Bean
    public MedicineEventPublisher medicineEventPublisher(RabbitTemplate rabbitTemplate) {
        return new AmqpMedicineEventPublisher(rabbitTemplate, exchangeName);
    }

}
