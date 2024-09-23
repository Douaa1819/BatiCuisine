package Repository.interfaces;

import models.Devis;

import java.util.Optional;
import java.util.UUID;

public interface DevisRepository {
    void save(Devis devis);
    Optional<Devis> getById(UUID id);
}
