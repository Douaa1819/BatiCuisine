package Repository.interfaces;

import models.Client;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientRepository {
    Client save(Client client);
    Optional<Client> findById(UUID id);
    List<Client> findAll();

    List<Client> findClientsByName(String nomClient) throws SQLException;
}
