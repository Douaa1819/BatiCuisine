package Repository.interfaces;

import models.MainOeuvre;

import java.util.Optional;
import java.util.UUID;

public interface MainOeuvreRepository {

    void save (MainOeuvre mainOeuvre);

    Optional<MainOeuvre>findById(UUID id);
}
