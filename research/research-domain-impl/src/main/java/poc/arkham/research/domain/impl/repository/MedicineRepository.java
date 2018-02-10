package poc.arkham.research.domain.impl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import poc.arckham.research.domain.model.Medicine;

public interface MedicineRepository extends ElasticsearchRepository<Medicine, String> {
}