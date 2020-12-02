package org.notabarista;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author codrea.tudor
 */
@SpringBootApplication
@ComponentScan({ "org.notabarista", "org.notabarista.repository" })
@Import(CommonLibApplication.class)
@EnableWebMvc
@EnableSwagger2
@EnableAutoConfiguration
@EnableScheduling
@EnableCaching
@EnableAsync
public class UserManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementServiceApplication.class, args);
	}

}
