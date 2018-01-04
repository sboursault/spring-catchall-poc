package poc.arkham.treatment.adapter.medicine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MedicineEventSubscriber {

  private Logger logger = LoggerFactory.getLogger(MedicineEventSubscriber.class);

  public void receive(String message) {
    logger.info("Received message '{}'", message);
  }


}