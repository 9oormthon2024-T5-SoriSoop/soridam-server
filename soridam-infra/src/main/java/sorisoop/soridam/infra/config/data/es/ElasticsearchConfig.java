package sorisoop.soridam.infra.config.data.es;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import lombok.RequiredArgsConstructor;
import sorisoop.soridam.infra.config.base.SoriDamConfig;

@Configuration
@RequiredArgsConstructor
@EnableElasticsearchRepositories(basePackages = "sorisoop.soridam.infra.repository.es")
public class ElasticsearchConfig implements SoriDamConfig {
	private final ElasticsearchProperties elasticsearchProperties;

	@Bean
	public ElasticsearchClient elasticsearchClient() {
		RestClient restClient = RestClient.builder(
				HttpHost.create(elasticsearchProperties.getUris())
			)
			.setDefaultHeaders(new Header[] {
				new BasicHeader("Authorization", basicAuth(
					elasticsearchProperties.getUsername(),
					elasticsearchProperties.getPassword()
				))
			})
			.build();

		ElasticsearchTransport transport = new RestClientTransport(
			restClient, new JacksonJsonpMapper()
		);

		return new ElasticsearchClient(transport);
	}

	private String basicAuth(String username, String password) {
		String auth = username + ":" + password;
		return "Basic " + Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
	}

}

