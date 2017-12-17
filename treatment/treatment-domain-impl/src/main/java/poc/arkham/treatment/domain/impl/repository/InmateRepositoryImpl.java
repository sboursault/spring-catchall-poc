package poc.arkham.treatment.domain.impl.repository;

import org.apache.commons.lang.math.IntRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import poc.arkham.common.util.PartialResult;
import poc.arkham.treatment.domain.model.Inmate;

import java.util.List;
import java.util.Optional;

@Repository
class InmateRepositoryImpl implements InmateRepository {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public Optional<Inmate> findById(String input) {
        Assert.hasLength(input, "[Assertion failed] - input must have length; it must not be null or empty");
        return Optional.ofNullable(mongoOperations.findById(input, Inmate.class));
    }

    @Override
    public Inmate save(Inmate inmate) {
        Assert.notNull(inmate, "[Assertion failed] - inmate is required; it must not be null");
        mongoOperations.save(inmate);
        return inmate;
    }

    @Override
    public PartialResult<Inmate> find(IntRange range) {
        final Query query = new Query();
        long count = mongoOperations.count(query, Inmate.class);
        final List<Inmate> results = mongoOperations.find(query.skip(range.getMinimumInteger())
                .limit(range.toArray().length), Inmate.class);
        return new PartialResult(results, range, Long.valueOf(count).intValue());
    }

    @Override
    public void deleteAll() {
        mongoOperations.remove(new Query(), Inmate.class);
    }
}
