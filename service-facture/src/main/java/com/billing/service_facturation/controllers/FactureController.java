package com.billing.service_facturation.controllers;


import com.billing.service_facturation.entities.Facture;
import com.billing.service_facturation.repositories.DetailFactureRepository;
import com.billing.service_facturation.repositories.FactureRepository;
import com.billing.service_facturation.services.ClientService;
import com.billing.service_facturation.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping(path = "/factures")
public class FactureController{
    @Autowired
    ClientService clientService;

    @Autowired
    ProduitService produitService;

    @Autowired
    FactureRepository factureRepository;

    @Autowired
    DetailFactureRepository detailFactureRepository;

    @GetMapping("/pf/{id}")
    public Optional<Facture> getFacture(@PathVariable("id") Long id){
        Optional<Facture> facture = factureRepository.findById(id);
        if(facture.isPresent()){
            facture.get().setClient(clientService.findClientById(facture.get().getClient_id()));
            facture.get().getDetails().forEach(d -> {
                d.setProduit(produitService.findProductById(d.getProduit_id()));
            });
        }
        return facture;
    }


}

