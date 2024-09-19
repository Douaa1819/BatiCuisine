package Services.interfaces;

import models.Materiaux;

import java.util.Optional;
import java.util.UUID;

public interface MateriauxService {
    void ajouterMateriaux(Materiaux materiaux);
    Optional<Materiaux> trouverMateriauxParId(UUID id);
}
