package webservice.BHXH;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "webservice.BXHX.dao")
public class BhxhApplication {

	public static void main(String[] args) {
		SpringApplication.run(BhxhApplication.class, args);
	}

}
