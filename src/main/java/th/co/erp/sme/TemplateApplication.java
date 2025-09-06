package th.co.erp.sme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableCaching
@SpringBootApplication
//@ComponentScan("th.co.my.sm")
@EnableJpaAuditing
@EnableJpaRepositories
public class TemplateApplication {


	public static void main(String[] args) {

		SpringApplication.run(TemplateApplication.class, args);
	}
}
