package poc.arckham.research.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import poc.arckham.research.domain.model.Medicine;

import java.util.List;

public interface MedicineSearchService {

    Page<Medicine> search(String query, Pageable pageable);
}
