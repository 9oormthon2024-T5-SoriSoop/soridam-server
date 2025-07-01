package sorisoop.soridam.infra.config.data.es;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableElasticsearchRepositories(basePackages = "sorisoop.soridam.infra.repository.es")
public class ElasticsearchConfig {
	private final ElasticsearchProperties elasticsearchProperties;

	@Bean
	public ElasticsearchClient elasticsearchClient() {
		return ElasticsearchClient.of(b -> b
			.host(elasticsearchProperties.getUris())
			.usernameAndPassword(
				elasticsearchProperties.getUsername(),
				elasticsearchProperties.getPassword()
			)
		);
	}
}

