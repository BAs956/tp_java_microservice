package com.billing.service_facturation.services;

import com.billing.service_facturation.datas.Produit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "SERVICE-PRODUIT")
public interface ProduitService {
    @GetMapping("/produits/{id}")
    public Produit findProductById(@PathVariable(name = "id") Long id);

    @GetMapping("/produits")
    public PagedModel<Produit> findAllProduits();

}
