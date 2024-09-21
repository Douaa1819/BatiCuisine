package Repository.interfaces;

import models.MainOeuvre;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MainOeuvreRepository {

    void save (MainOeuvre mainOeuvre);

    Optional<MainOeuvre>findById(UUID id);
    List<MainOeuvre> getMainOeuvreByProjetId(UUID projetId);
    double calculerCoutTotal(UUID projetId);
    double calculerCoutTotalAvantTVA(UUID projetId);
}
