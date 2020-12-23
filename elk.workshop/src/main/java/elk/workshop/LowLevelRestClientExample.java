package elk.workshop;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.RequestLine;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

public class LowLevelRestClientExample {

	public static void main(String[] args) throws IOException {
		showOrders();
	}
	
	public static void showOrders() throws IOException {
		RestClient client = RestClient.builder(new HttpHost("localhost", 9200, "http")).build();
		Request searchRequest = new Request("POST", "/order_data/_search");
		searchRequest.setJsonEntity("{ \"query\" : { \"match_all\" : {} } }");
		Response response = client.performRequest(searchRequest);
		String responseBody = EntityUtils.toString(response.getEntity());
		client.close();
		System.out.println(responseBody);
	}
	
	public static void showESInfo() throws IOException {
		RestClient client = RestClient.builder(new HttpHost("localhost", 9200, "http")).build();
		Response response = client.performRequest(new Request("GET", "/"));
		RequestLine requestLine = response.getRequestLine();
		HttpHost host = response.getHost();
		int statusCode = response.getStatusLine().getStatusCode();
		Header[] headers = response.getHeaders();
		String responseBody = EntityUtils.toString(response.getEntity());
		client.close();
		System.out.println(responseBody);
	}
	
}
