package poc.arkham.treatment.domain.service;

import org.apache.commons.lang.math.IntRange;
import org.springframework.data.domain.Page;
import poc.arkham.common.util.PartialResult;
import poc.arkham.treatment.domain.exception.InmateNotFoundException;
import poc.arkham.treatment.domain.exception.InvalidStateException;
import poc.arkham.treatment.domain.model.Inmate;

public interface InmateService {

    Inmate findById(String id) throws InmateNotFoundException;

    PartialResult<Inmate> find(IntRange pageable);

    Inmate register(Inmate inmate) throws InvalidStateException;
}
