package tech.devinhouse.devgram;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import tech.devinhouse.devgram.model.Perfil;
import tech.devinhouse.devgram.model.Status;
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
			List<Perfil> lista = perfilService.consultar(100, 0, "nome", "ASC");
			if (lista.isEmpty()) {
				perfilService.criar(new Perfil("tiago", "bio do tiago", LocalDate.now().minusYears(20),
						"dev", LocalDateTime.now(), LocalDateTime.now(), Status.ATIVO));
				perfilService.criar(new Perfil("fabiane", "bio do fabiane", LocalDate.now().minusYears(22),
						"dev", LocalDateTime.now(), LocalDateTime.now(), Status.ATIVO));
			}
		};
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

}
