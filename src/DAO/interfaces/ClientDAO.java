package DAO.interfaces;

import models.Client;
import models.Projet;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientDAO {
    Client createClient(Client client);
    Optional<Client> getClient(UUID id);
    List<Client> getAllClient();
    List<Client> getClientsByName(String nomClient) throws SQLException;

}
