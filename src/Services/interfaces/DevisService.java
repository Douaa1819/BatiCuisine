package Services.interfaces;

import models.Devis;

import java.util.Optional;
import java.util.UUID;

public interface DevisService {

    void save(Devis devis);
    Optional<Devis> getById(UUID id);
}