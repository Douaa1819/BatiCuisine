package Services.impl;

import Repository.interfaces.ClientRepository;
import Services.interfaces.ClientService;
import models.Client;

import java.sql.SQLException;
import java.util.*;

public class ClientServiceImpl implements ClientService {

    private  final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Optional<Client> getClientById(UUID id) {
        return clientRepository.findById(id);
    }

    @Override
    public Map<UUID, Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public List<Client> getClientsByName(String nomClient) {
        try {
            return clientRepository.findClientsByName(nomClient);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche des clients par nom : " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
