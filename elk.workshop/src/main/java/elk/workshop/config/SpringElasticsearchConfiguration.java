package elk.workshop.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Component;

import fr.pilato.spring.elasticsearch.ElasticsearchRestClientFactoryBean;

@Component
@EnableElasticsearchRepositories(basePackages = "elk.workshop.repository")
public class SpringElasticsearchConfiguration extends AbstractElasticsearchConfiguration {

	@Bean
	public RestHighLevelClient esClient() throws Exception {
		ElasticsearchRestClientFactoryBean factory = new ElasticsearchRestClientFactoryBean();
		factory.setEsNodes(new String[] { "http://127.0.0.1:9200" });
//		factory.setProperties(<es_properties>);
//		factory.setClasspathRoot("<root_mappings_path>");
//		factory.setMappings(new String[] { "<mapping_path>" });
		factory.setMergeSettings(true);
		factory.afterPropertiesSet();
		return factory.getObject();
	}

	@Override
	public RestHighLevelClient elasticsearchClient() {
		try {
			return esClient();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
