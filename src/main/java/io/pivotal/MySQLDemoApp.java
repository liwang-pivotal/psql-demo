package io.pivotal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "io.pivotal.repo")
public class MySQLDemoApp {

	public static void main(String[] args) {
		SpringApplication.run(MySQLDemoApp.class, args);
	}
	
}
