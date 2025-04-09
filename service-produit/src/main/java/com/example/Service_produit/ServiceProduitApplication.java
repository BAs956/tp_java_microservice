package com.example.Service_produit;

import com.example.Service_produit.Entite.Produit;
import com.example.Service_produit.Repository.ProduitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class ServiceProduitApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceProduitApplication.class, args);
	}

//	@Bean
//	CommandLineRunner start(ProduitRepository produitRepository,
//							RepositoryRestConfiguration restConfiguration) {
//		return args -> {
//			restConfiguration.exposeIdsFor(Produit.class);
//			produitRepository.save(new Produit(null, "Sac", 4500, "Sac de luxe"));
//			produitRepository.save(new Produit(null, "Robe", 8500, "Robe de soiree"));
//			produitRepository.save(new Produit(null, "Rouge a levres", 2500, "Rouge a levre nouvelle gamme."));
//		};
//	}
}
