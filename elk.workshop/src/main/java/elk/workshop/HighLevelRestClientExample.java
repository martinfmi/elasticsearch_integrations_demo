package elk.workshop;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;


public class HighLevelRestClientExample {

	public static void main(String[] args) throws IOException {
		showOrders();
	}
	
	public static void showOrders() throws IOException {
		RestHighLevelClient client = new RestHighLevelClient(
		        RestClient.builder(
		                new HttpHost("localhost", 9200, "http")));
		SearchRequest searchRequest = new SearchRequest("order_data"); 
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 
		searchSourceBuilder.query(QueryBuilders.matchAllQuery()); 
		searchRequest.source(searchSourceBuilder); 
		SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
		for(SearchHit hit : response.getHits().getHits()) {
			System.out.println(hit.getSourceAsString());
		}
		client.close();
	}
	
}
