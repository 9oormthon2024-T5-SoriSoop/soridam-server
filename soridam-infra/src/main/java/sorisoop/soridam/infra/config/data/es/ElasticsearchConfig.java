package sorisoop.soridam.infra.config.data.es;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "sorisoop.soridam.infra.repository.es")
public class ElasticsearchConfig {
}

