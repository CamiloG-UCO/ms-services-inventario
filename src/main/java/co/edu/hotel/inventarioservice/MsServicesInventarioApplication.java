package co.edu.hotel.inventarioservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MsServicesInventarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsServicesInventarioApplication.class, args);
	}


	@Bean
	WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")  // Cambiado a "**" para permitir todas las rutas
						.allowedOrigins("http://localhost:4200")
						.allowedMethods("GET", "POST", "PUT", "DELETE")  // Métodos permitidos
						.allowedHeaders("*")  // Permitir todos los headers
						.allowCredentials(true);
			}
		};
	}

}
