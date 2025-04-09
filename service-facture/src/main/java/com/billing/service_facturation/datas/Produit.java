package com.billing.service_facturation.datas;

import lombok.Data;

@Data
public class Produit {
    private Long id;
    private String nom;
    private String description;
    private double prix;
}
