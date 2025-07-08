package sorisoop.soridam.infra.config.data.es;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Component
@NoArgsConstructor
@AllArgsConstructor
//@ConfigurationProperties(prefix = "spring.elasticsearch")
public class ElasticsearchProperties {
	private String uris;
	private String username;
	private String password;
}