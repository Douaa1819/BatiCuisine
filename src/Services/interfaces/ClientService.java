package Services.interfaces;

import models.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientService {
    Client createClient(Client client);
    Optional<Client> getClientById(UUID id);
    List<Client> getAllClients();
}
