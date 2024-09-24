package DAO.interfaces;

import models.Materiaux;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MateriauxDAO {
    void create(Materiaux materiaux);
    List<Materiaux> getMateriauxByProjetId(UUID projetId);


}
