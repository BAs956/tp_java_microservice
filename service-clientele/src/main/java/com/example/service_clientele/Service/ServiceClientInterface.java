package com.example.service_clientele.Service;

import com.example.service_clientele.Entite.Client;

import java.util.List;
import java.util.Optional;

public interface ServiceClientInterface {
    public List<Client> findAll();
    public Client save(Client model);
    public Client update(Client model, Long id);
    public Optional<Client> find(Long id);
    public boolean delete(Long id);
}