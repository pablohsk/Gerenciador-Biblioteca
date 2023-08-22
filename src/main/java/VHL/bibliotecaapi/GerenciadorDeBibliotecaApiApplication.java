package VHL.bibliotecaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = "VHL.bibliotecaapi")
@ComponentScan(basePackages = "VHL.bibliotecaapi")
public class GerenciadorDeBibliotecaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorDeBibliotecaApiApplication.class, args);
	}

}