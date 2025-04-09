package com.billing.service_facturation.controllers;



import com.billing.service_facturation.entities.DetailFacture;
import com.billing.service_facturation.repositories.FactureRepository;
import com.billing.service_facturation.services.DetailFactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/facture-details")
public class DetailFactureController {
}

