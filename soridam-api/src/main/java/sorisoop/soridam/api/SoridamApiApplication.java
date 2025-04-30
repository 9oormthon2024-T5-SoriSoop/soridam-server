package sorisoop.soridam.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SoridamApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoridamApiApplication.class, args);
	}

}
