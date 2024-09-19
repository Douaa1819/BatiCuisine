package DAO.interfaces;

import models.Client;
import models.Projet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientDAO {
    Client create(Client client);
    Optional<Client> read(UUID id);
    List<Client> readAll();

}
