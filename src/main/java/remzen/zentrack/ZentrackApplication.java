package remzen.zentrack;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import remzen.zentrack.models.users.model.BeanUser;
import remzen.zentrack.models.users.model.ERol;
import remzen.zentrack.models.users.model.UserRepository;

@SpringBootApplication
public class ZentrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZentrackApplication.class, args);
	}

@Bean
CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			// Verificar si ya existe el usuario admin
			if (userRepository.findByUsername("angel").isEmpty()) {
				// Crear y guardar el usuario admin
				BeanUser adminUser = BeanUser.builder()
						.username("angel")
						.password(passwordEncoder.encode("adminsito")) // Contraseña encriptada
						.secretWord(passwordEncoder.encode("Pacificagod")) // Palabra secreta encriptada
						.role(ERol.ADMIN)
						.build();

				userRepository.save(adminUser);
				System.out.println("Usuario administrador creado exitosamente!");
			}
		};
	}

}
