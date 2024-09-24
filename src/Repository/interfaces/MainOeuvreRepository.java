package Repository.interfaces;

import models.MainOeuvre;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MainOeuvreRepository {

    void save (MainOeuvre mainOeuvre);
    List<MainOeuvre> getMainOeuvreByProjetId(UUID projetId);
}
