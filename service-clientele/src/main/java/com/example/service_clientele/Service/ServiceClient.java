package com.example.service_clientele.Service;

import com.example.service_clientele.Entite.Client;
import com.example.service_clientele.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ServiceClient implements ServiceClientInterface {
    @Autowired
    protected ClientRepository repository;

    @Override
    public List<Client> findAll() {
        return repository.findAll();
    }

    @Override
    public Client save(Client model) {
        return repository.save(model);
    }

    @Override
    public Client update(Client model, Long id) {
        model.setId(id);
        return repository.save(model);
    }

    @Override
    public Optional<Client> find(Long id) {
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
