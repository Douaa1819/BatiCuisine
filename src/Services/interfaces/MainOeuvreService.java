package Services.interfaces;

import models.MainOeuvre;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MainOeuvreService {

    void ajouterMainOeuvre(MainOeuvre mainOeuvre);
    List<MainOeuvre> getMainOeuvreByProjetId(UUID projetId);
}
