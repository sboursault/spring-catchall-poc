package poc.arkham.research.domain.impl.service;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
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
        QueryBuilder queryBuilder;
        if (StringUtils.isEmpty(text)) {
            queryBuilder = new MatchAllQueryBuilder();
        } else {
            queryBuilder = new MultiMatchQueryBuilder(text)
                    .field("label", 2)
                    .field("description")
                    .fuzziness(ONE);
        }
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withPageable(pageable)
                .build();
        return operations.queryForPage(query, Medicine.class);
    }
}
