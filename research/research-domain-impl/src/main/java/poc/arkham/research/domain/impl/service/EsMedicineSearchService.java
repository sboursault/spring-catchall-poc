package poc.arkham.research.domain.impl.service;

import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import poc.arckham.research.domain.model.Medicine;
import poc.arckham.research.domain.service.MedicineSearchService;

import static org.elasticsearch.common.unit.Fuzziness.ONE;

public class EsMedicineSearchService implements MedicineSearchService {

    private ElasticsearchOperations operations;

    public EsMedicineSearchService(ElasticsearchOperations operations) {
        this.operations = operations;
    }

    @Override
    public Page<Medicine> search(String text, Pageable pageable) {
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(new MultiMatchQueryBuilder(text)
                        .field("label", 2)
                        .field("description")
                        .fuzziness(ONE))
                .withPageable(pageable)
                .build();
        return operations.queryForPage(query, Medicine.class);
    }
}
