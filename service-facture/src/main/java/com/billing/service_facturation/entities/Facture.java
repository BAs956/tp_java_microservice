package com.billing.service_facturation.entities;

import com.billing.service_facturation.datas.Client;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "factures")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long client_id;
    @Transient
    private Client client;
    private Date date;
    @OneToMany(mappedBy = "facture_id", targetEntity = DetailFacture.class, cascade = CascadeType.REMOVE)
    private Collection<DetailFacture> details;
}