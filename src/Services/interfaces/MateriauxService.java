package Services.interfaces;

import models.Materiaux;

import java.util.List;
import java.util.UUID;

public interface MateriauxService {
    void ajouterMateriaux(Materiaux materiaux);
    List<Materiaux> getMateriauxByProjetId(UUID projetId);
}
