package it.galactic.command.stargateadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "it.galactic.command.stargateadmin.repository")
@EntityScan(basePackages = "it.galactic.command.stargateadmin.entity")

@SpringBootApplication
public class StargateadminApplication {

	public static void main(String[] args) {
		SpringApplication.run(StargateadminApplication.class, args);
	}

}
