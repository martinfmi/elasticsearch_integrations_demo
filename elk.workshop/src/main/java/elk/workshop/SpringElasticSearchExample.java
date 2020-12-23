package elk.workshop;

import java.io.IOException;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringElasticSearchExample {

	@Autowired
	private RestHighLevelClient client;

	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext context = null;
		try {
			context = new AnnotationConfigApplicationContext();
			context.scan("elk.workshop");
			context.refresh();
			SpringElasticSearchExample app = context.getBean(SpringElasticSearchExample.class);
			app.findAllOrders();
		} finally {
			if (context != null) {
				context.close();
			}
		}
	}
	
	public void findAllOrders() throws IOException {
		SearchRequest searchRequest = new SearchRequest("order_data");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchAllQuery());
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		SearchHits hits = searchResponse.getHits();
		System.out.println(hits.getHits().length);
		for (SearchHit hit : hits.getHits()) {
			System.out.println(hit.getSourceAsString());
		}

	}
}
