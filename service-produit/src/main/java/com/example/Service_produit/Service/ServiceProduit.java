package com.example.Service_produit.Service;


import com.example.Service_produit.Entite.Produit;
import com.example.Service_produit.Repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ServiceProduit implements ServiceProduitInterface {
    @Autowired
    protected ProduitRepository repository;

    @Override
    public List<Produit> findAll() {
        return repository.findAll();
    }

    @Override
    public Produit save(Produit model) {
        return repository.save(model);
    }

    @Override
    public Produit update(Produit model, Long id) {
        model.setId(id);
        return repository.save(model);
    }

    @Override
    public Optional<Produit> find(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean delete(Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
