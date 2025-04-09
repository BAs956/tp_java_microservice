package com.billing.service_facturation.services;


import com.billing.service_facturation.entities.Facture;
import com.billing.service_facturation.repositories.FactureRepository;
import com.billing.service_facturation.services.interfaces.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FactureService extends CrudService<Facture, Long> {

    @Autowired
    protected FactureRepository repository;


}

