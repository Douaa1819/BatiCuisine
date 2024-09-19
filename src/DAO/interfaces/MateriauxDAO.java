package DAO.interfaces;

import models.Materiaux;
import java.util.Optional;
import java.util.UUID;

public interface MateriauxDAO {
    void create(Materiaux materiaux);
    Optional<Materiaux> findById(UUID id);
}
