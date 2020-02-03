package curso.api.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableAutoConfiguration
@EnableWebMvc
@RestController
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "curso.api.rest.repository" })
@ComponentScan(basePackages = { "curso.*" })
@EntityScan(basePackages = { "curso.api.rest.model" })

public class CursospringrestapiApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(CursospringrestapiApplication.class, args);
	/*	System.out.println(new BCryptPasswordEncoder().encode("31102010"));*/

	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		/*
		 * liberação para admin ou varios tipos de acessos quando usado ** libera para
		 * todos
		 */
		registry.addMapping("/**").allowedMethods("*")/* pode ser liberado para POST, GET, PUT e DELETE */
				.allowedOrigins("*");/*
										 * Liberando para usuario do servidor // Liberacao de mapeamento para os
										 * usuarios de todas as origens
										 */
	}

}
