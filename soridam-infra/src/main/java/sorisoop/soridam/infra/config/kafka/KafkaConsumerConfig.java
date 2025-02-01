package sorisoop.soridam.infra.config.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String BOOTSTRAP_SERVERS;

	private Map<String, Object> commonConsumerProps() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return props;
	}

	@Bean
	public ConsumerFactory<String, Object> userConsumer() {
		Map<String, Object> props = new HashMap<>(commonConsumerProps());
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "user-events-group");
		return new DefaultKafkaConsumerFactory<>(props);
	}

	@Bean
	public ConsumerFactory<String, Object> reviewConsumer() {
		Map<String, Object> props = new HashMap<>(commonConsumerProps());
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "review-events-group");
		return new DefaultKafkaConsumerFactory<>(props);
	}


	@Bean
	public ConsumerFactory<String, Object> noiseConsumer() {
		Map<String, Object> props = new HashMap<>(commonConsumerProps());
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "noise-events-group");
		return new DefaultKafkaConsumerFactory<>(props);
	}


	@Bean(name = "userEventsGroup")
	public ConcurrentKafkaListenerContainerFactory<String, Object> userKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(userConsumer());
		factory.setConcurrency(3); // 병렬 소비자 수 설정
		factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
		return factory;
	}

	@Bean(name = "reviewEventsGroup")
	public ConcurrentKafkaListenerContainerFactory<String, Object> reviewKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(reviewConsumer());
		factory.setConcurrency(2); // 병렬 소비자 수 설정
		factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
		return factory;
	}

	@Bean(name = "noiseEventsGroup")
	public ConcurrentKafkaListenerContainerFactory<String, Object> noiseKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(noiseConsumer());
		factory.setConcurrency(1); // 병렬 소비자 수 설정
		factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
		return factory;
	}
}

