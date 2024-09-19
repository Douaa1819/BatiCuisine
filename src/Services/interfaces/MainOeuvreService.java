package Services.interfaces;

import models.MainOeuvre;

import java.util.Optional;
import java.util.UUID;

public interface MainOeuvreService {

    void ajouterMainOeuvre(MainOeuvre mainOeuvre);
    Optional<MainOeuvre> trouverMainOeuvreParId(UUID id);
}
