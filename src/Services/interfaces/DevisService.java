package Services.interfaces;

import models.Devis;

import java.util.UUID;

public interface DevisService {

    void save(Devis devis);
    Devis getById(UUID id);
}