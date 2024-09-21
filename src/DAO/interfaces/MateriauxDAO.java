package DAO.interfaces;

import models.Materiaux;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MateriauxDAO {
    void create(Materiaux materiaux);
    Optional<Materiaux> findById(UUID id);
    List<Materiaux> getMateriauxByProjetId(UUID projetId);
    double calculerCoutTotal(UUID projetId);
    double calculerCoutTotalAvantTVA(UUID projetId);


}
