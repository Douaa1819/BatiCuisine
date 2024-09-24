package Services.interfaces;

import models.Devis;

import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public interface DevisService {

    void save(Devis devis);

    Optional<Devis> getDevisByProjetId(UUID projetId)  throws SQLException;
}