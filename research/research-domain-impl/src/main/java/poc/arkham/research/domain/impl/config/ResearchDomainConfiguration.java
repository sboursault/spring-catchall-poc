package poc.arkham.research.domain.impl.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import poc.arckham.research.domain.service.MedicineEventPublisher;
import poc.arckham.research.domain.service.MedicineSearchService;
import poc.arkham.research.domain.impl.repository.MedicineRepository;
import poc.arkham.research.domain.impl.service.DefaultMedicineService;

import java.net.InetAddress;
import java.net.UnknownHostException;
import poc.arkham.research.domain.impl.service.EsMedicineSearchService;

/*
 * Module configuration
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "poc.arkham.research.domain.impl.repository")
public class ResearchDomainConfiguration {

    @Bean
    public DefaultMedicineService medicineService(MedicineEventPublisher medicineEventPublisher, MedicineRepository repository) {
        return new DefaultMedicineService(repository, medicineEventPublisher);
    }

    @Bean
    public MedicineSearchService medicineSearchService(ElasticsearchOperations operations) {
        return new EsMedicineSearchService(operations);
    }


    @Bean
    public ElasticsearchOperations elasticsearchTemplate(@Value("${elasticsearch.host:localhost}") String host,
            @Value("${elasticsearch.port:9300}") int port) throws UnknownHostException {

        //Settings esSettings = Settings.settingsBuilder()
        //        .put("cluster.name", "myEsCluster")
        //        .build();


        TransportClient transportClient = TransportClient.builder()
                //.settings(esSettings)
                .build()
                .addTransportAddress(
                        new InetSocketTransportAddress(InetAddress.getByName(host), port));

        // when to choose a transport or a node client :
        // https://www.elastic.co/guide/en/elasticsearch/guide/current/_transport_client_versus_node_client.html

        return new ElasticsearchTemplate(transportClient);
    }

}
