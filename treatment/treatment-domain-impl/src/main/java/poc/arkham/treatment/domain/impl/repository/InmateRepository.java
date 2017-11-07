package poc.arkham.treatment.domain.impl.repository;

import poc.arkham.treatment.domain.model.Inmate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InmateRepository extends MongoRepository<Inmate, String> {

    Inmate findByAka(String aka);

}