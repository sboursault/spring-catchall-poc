package poc.arkham.treatment.domain.impl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import poc.arkham.treatment.domain.model.Inmate;

import java.util.Optional;

public interface InmateRepository {

    Optional<Inmate> findById(String s);

    Inmate save(Inmate inmate);

    Page<Inmate> find(Pageable pageable);

    void deleteAll();
}