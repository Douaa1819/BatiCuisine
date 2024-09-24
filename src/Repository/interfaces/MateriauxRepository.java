package Repository.interfaces;

import models.Materiaux;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MateriauxRepository {

    void save(Materiaux materiaux);
    List<Materiaux> getMateriauxByProjetId(UUID projetId);
}
