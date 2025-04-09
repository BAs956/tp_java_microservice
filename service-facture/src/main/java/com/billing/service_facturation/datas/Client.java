package com.billing.service_facturation.datas;

import lombok.Data;

@Data
public class Client {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
}
