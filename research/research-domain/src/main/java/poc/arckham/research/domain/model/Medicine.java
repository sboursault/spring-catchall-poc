package poc.arckham.research.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Optional;
/*
 * To find meds using the elasticsearch api: curl http://localhost:9200/research/_search?pretty=true
 */
@Data
@Builder(builderMethodName = "newMedicine")
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "research", type = "medicines", shards = 1, replicas = 0, refreshInterval = "-1")
public class Medicine {

    @Id
    private String id;

    private String label;

    private String description;

    private MedicineState state; // TODO should change based on actions, see spring state machine

    private Optional<Money> averagePrice;

}
