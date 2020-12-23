package elk.workshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import elk.workshop.repository.model.OrderData;

public interface OrderDataRepository extends ElasticsearchRepository<OrderData, String> {
 
    Page<OrderData> findBySerialNumber(String serialNumber, Pageable pageable);
 
    @Query("{\"bool\": {\"must\": [{\"match\": {\"serialNumber\": \"?0\"}}]}}")
    Page<OrderData> findBySerialNumberUsingCustomQuery(String name, Pageable pageable);
}
