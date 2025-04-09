package com.billing.service_facturation.services;


import com.billing.service_facturation.entities.DetailFacture;
import com.billing.service_facturation.repositories.DetailFactureRepository;
import com.billing.service_facturation.services.interfaces.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DetailFactureService extends CrudService<DetailFacture, Long> {

    @Autowired
    protected DetailFactureRepository repository;
}
