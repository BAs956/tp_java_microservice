package com.billing.service_facturation;

import com.billing.service_facturation.datas.Client;
import com.billing.service_facturation.entities.DetailFacture;
import com.billing.service_facturation.entities.Facture;
import com.billing.service_facturation.repositories.DetailFactureRepository;
import com.billing.service_facturation.repositories.FactureRepository;
import com.billing.service_facturation.services.ClientService;
import com.billing.service_facturation.services.ProduitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class ServiceFacturationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceFacturationApplication.class, args);
	}


	@Bean
	CommandLineRunner start(FactureRepository factureRepository, DetailFactureRepository detailFactureRepository, ClientService clientService, ProduitService produitService){
		return args -> {
			Client client = clientService.findClientById(1L);
			Facture facture = factureRepository.save(new Facture(null, client.getId(), null, new Date(), null));
			produitService.findAllProduits().getContent().forEach(produit -> {
				detailFactureRepository.save(new DetailFacture(null, produit.getId(), null, 1, produit.getPrix(), produit.getId()));
			});
		};
	}
}
