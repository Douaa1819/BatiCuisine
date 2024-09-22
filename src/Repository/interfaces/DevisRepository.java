package Repository.interfaces;

import models.Devis;

import java.util.UUID;

public interface DevisRepository {
    void save(Devis devis);
    Devis getById(UUID id);
}
