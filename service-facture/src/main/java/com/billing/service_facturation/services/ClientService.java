package com.billing.service_facturation.services;

import com.billing.service_facturation.datas.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SERVICE-CLIENT")
public interface ClientService {
    @GetMapping("/clients/{id}")
    public Client findClientById(@PathVariable(name = "id") Long id);
}
