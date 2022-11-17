package tech.devinhouse.devgram;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tech.devinhouse.devgram.model.Perfil;
import tech.devinhouse.devgram.service.PerfilService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class DevgramApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevgramApplication.class, args);
	}

	@Bean
	CommandLineRunner run(PerfilService perfilService) {
		return args -> {
			List<Perfil> lista = perfilService.consultar();
			if (lista.isEmpty()) {
				perfilService.criar(new Perfil("tiago", "bio do tiago", LocalDate.now().minusYears(20),
						"dev", LocalDateTime.now(), LocalDateTime.now()));
				perfilService.criar(new Perfil("fabiane", "bio do fabiane", LocalDate.now().minusYears(22),
						"dev", LocalDateTime.now(), LocalDateTime.now()));
			}
		};
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
