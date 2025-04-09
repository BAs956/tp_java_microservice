package com.billing.service_facturation.entities;

import com.billing.service_facturation.datas.Produit;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "facture_details")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class DetailFacture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long produit_id;
    @Transient
    private Produit produit;
    private Integer quantite;
    private double prix;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long facture_id;
}