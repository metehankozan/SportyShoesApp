package com.sportyshoes.SportyShoesApp;

import com.sportyshoes.SportyShoesApp.entity.User;
import com.sportyshoes.SportyShoesApp.repository.UserRepository;
import com.sportyshoes.SportyShoesApp.type.UserType;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootApplication
@AllArgsConstructor
public class SportyShoesAppApplication {

	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SportyShoesAppApplication.class, args);
	}

	@PostConstruct
	private void init() {
		Optional<User> user = userRepository.findById("admin@gmail.com");
		if (user.isEmpty()) {
			User admin = new User("admin@gmail.com", "12345", UserType.ADMIN.value(), LocalDateTime.now());
			userRepository.save(admin);
		}
	}
}
