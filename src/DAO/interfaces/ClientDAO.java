package DAO.interfaces;

import models.Client;
import models.Projet;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientDAO {
    Client create(Client client);
    Optional<Client> read(UUID id);
    List<Client> readAll();
    List<Client> getClientsByName(String nomClient) throws SQLException;

}
