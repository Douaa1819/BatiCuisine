package Repository.impl;

import DAO.interfaces.ClientDAO;
import Repository.interfaces.ClientRepository;
import models.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ClientRepositoryImpl  implements ClientRepository {
    private final ClientDAO clientDAO;

    public ClientRepositoryImpl(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    public Client save(Client client) {
        return clientDAO.create(client);
    }

    @Override
    public Optional<Client> findById(UUID id) {
        return clientDAO.read(id);
    }

    @Override
    public List<Client> findAll() {
        return clientDAO.readAll();
    }
}

