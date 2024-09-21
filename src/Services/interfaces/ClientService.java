package Services.interfaces;

import models.Client;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientService {
    Client createClient(Client client);
    Optional<Client> getClientById(UUID id);
    List<Client> getAllClients();
    List<Client> getClientsByName(String nomClient);
}
