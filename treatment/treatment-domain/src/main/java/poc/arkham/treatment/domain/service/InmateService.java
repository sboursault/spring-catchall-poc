package poc.arkham.treatment.domain.service;

import poc.arkham.treatment.domain.exception.InmateNotFoundException;
import poc.arkham.treatment.domain.exception.InvalidStateException;
import poc.arkham.treatment.domain.model.Inmate;

import java.util.List;

public interface InmateService {

    Inmate findById(String id) throws InmateNotFoundException;

    List<Inmate> findAll();

    Inmate register(Inmate inmate) throws InvalidStateException;
}
