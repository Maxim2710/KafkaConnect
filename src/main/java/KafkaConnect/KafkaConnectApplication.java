package KafkaConnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Основной класс для запуска приложения Spring Boot.
 */
@SpringBootApplication
public class KafkaConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaConnectApplication.class, args);
	}

}
