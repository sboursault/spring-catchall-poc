package poc.arkham.treatment.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import poc.arkham.treatment.domain.exception.InmateNotFoundException;
import poc.arkham.treatment.domain.exception.InvalidStateException;
import poc.arkham.treatment.domain.model.Inmate;

public interface InmateService {

    Inmate findById(String id) throws InmateNotFoundException;

    Page<Inmate> find(Pageable pageable);

    Inmate register(Inmate inmate) throws InvalidStateException;
}
