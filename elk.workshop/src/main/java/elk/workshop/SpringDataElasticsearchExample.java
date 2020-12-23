package elk.workshop;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Component;

import elk.workshop.repository.OrderDataRepository;
import elk.workshop.repository.model.OrderData;

@Component
public class SpringDataElasticsearchExample {

	@Autowired
	private OrderDataRepository repository;
	
	@Autowired
	private ElasticsearchRestTemplate template;
	
	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext context = null;
		try {
			context = new AnnotationConfigApplicationContext();
			context.scan("elk.workshop");
			context.refresh();
			SpringDataElasticsearchExample app = context.getBean(SpringDataElasticsearchExample.class);
			OrderData order = app.findBySerialNumber("X507UA");
			System.out.println(order.getDescription());
		} finally {
			if (context != null) {
				context.close();
			}
		}
	}
	
	public OrderData findBySerialNumber(String serialNumber) {
		return repository.findBySerialNumber(serialNumber, PageRequest.of(0, 10)).getContent().get(0);
	}
	
}
