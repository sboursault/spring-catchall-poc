package poc.arkham.treatment.domain.impl.repository;

import org.apache.commons.lang.math.IntRange;
import poc.arkham.common.util.PartialResult;
import poc.arkham.treatment.domain.model.Inmate;

import java.util.Optional;

public interface InmateRepository {

    Optional<Inmate> findById(String s);

    Inmate save(Inmate inmate);

    PartialResult<Inmate> find(IntRange range);

    void deleteAll();
}