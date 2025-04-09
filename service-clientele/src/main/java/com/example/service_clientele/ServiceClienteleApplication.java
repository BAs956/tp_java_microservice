package com.example.service_clientele;

import com.example.service_clientele.Entite.Client;
import com.example.service_clientele.Repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class ServiceClienteleApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceClienteleApplication.class, args);
	}

//	@Bean
//	CommandLineRunner start(ClientRepository clientRepository,
//							RepositoryRestConfiguration restConfiguration) {
//		return args -> {
//			restConfiguration.exposeIdsFor(Client.class);
//			clientRepository.save(new Client(null, "TOTO", "Titi", "titi@gmail.com"));
//			clientRepository.save(new Client(null, "ABALO", "Jules", "jules@gmail.com"));
//			clientRepository.save(new Client(null, "DANSOU", "Noko", "noko@gmail.com"));
//		};
//	}
}