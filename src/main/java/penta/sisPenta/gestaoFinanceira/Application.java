package penta.sisPenta.gestaoFinanceira;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
@EntityScan({"penta.sisPenta"})
@EnableJpaRepositories({"penta.sisPenta"})
@ComponentScan({"penta.sisPenta", "penta.sisPenta.Controller", "penta.sisPenta.Configuration", "penta.sisPenta.Reports"})
@EnableCaching
@Configuration
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
