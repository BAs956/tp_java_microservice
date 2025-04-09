package com.example.Service_produit.Service;

import com.example.Service_produit.Entite.Produit;


import java.util.List;
import java.util.Optional;

public interface ServiceProduitInterface {
    public List<Produit> findAll();
    public Produit save(Produit model);
    public Produit update(Produit model, Long id);
    public Optional<Produit> find(Long id);
    public boolean delete(Long id);
}