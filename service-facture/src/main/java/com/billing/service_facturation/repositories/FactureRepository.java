package com.billing.service_facturation.repositories;

import com.billing.service_facturation.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;



public interface FactureRepository extends JpaRepository<Facture, Long> {
}
