package poc.arkham.research.adapter.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import poc.arckham.research.domain.model.Medicine;
import poc.arckham.research.domain.service.MedicineEventPublisher;


public class AmqpMedicineEventPublisher implements MedicineEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    private final String exchange;

    public AmqpMedicineEventPublisher(RabbitTemplate rabbitTemplate, String exchange) {
      this.rabbitTemplate = rabbitTemplate;
      this.exchange = exchange;
    }

    // TODO: what if the message is published and the consumer is offline ?

    @Override
    public void sendAuthorized(Medicine medicine) {
        String routingKey = "medicine.authorized";
        String message = "medicine " + medicine.getLabel() + "authorized";
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
