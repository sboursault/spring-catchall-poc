package poc.arkham.inmate.domain.impl.repository;

import poc.arkham.inmate.domain.model.Inmate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InmateRepository extends MongoRepository<Inmate, String> {

    Inmate findByAka(String aka);

}