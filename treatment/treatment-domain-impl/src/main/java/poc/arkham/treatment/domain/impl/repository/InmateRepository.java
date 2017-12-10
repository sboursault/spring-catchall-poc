package poc.arkham.treatment.domain.impl.repository;

import poc.arkham.treatment.domain.model.Inmate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface InmateRepository {

    Optional<Inmate> findById(String s);

    Inmate save(Inmate inmate);

    List<Inmate> findAll();

    void deleteAll();
}