package com.billing.service_facturation.repositories;

import com.billing.service_facturation.entities.DetailFacture;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DetailFactureRepository extends JpaRepository<DetailFacture, Long> {

}
