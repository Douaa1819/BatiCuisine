package Repository.interfaces;

import models.Materiaux;

import java.util.Optional;
import java.util.UUID;

public interface MateriauxRepository {

    void save(Materiaux materiaux);
    Optional<Materiaux> findById(UUID id);
}
