package Repository.interfaces;

import models.Materiaux;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MateriauxRepository {

    void save(Materiaux materiaux);
    Optional<Materiaux> findById(UUID id);

    List<Materiaux> getMateriauxByProjetId(UUID projetId);
    double calculerCoutTotal(UUID projetId);
}
