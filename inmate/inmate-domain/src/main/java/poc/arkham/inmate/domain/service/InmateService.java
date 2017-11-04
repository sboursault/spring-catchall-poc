package poc.arkham.inmate.domain.service;

import poc.arkham.inmate.domain.exception.InmateNotFoundException;
import poc.arkham.inmate.domain.exception.InvalidStateException;
import poc.arkham.inmate.domain.model.Inmate;

import java.util.List;

public interface InmateService {

    Inmate findById(String id) throws InmateNotFoundException;

    List<Inmate> findAll();

    Inmate register(Inmate inmate) throws InvalidStateException;
}
