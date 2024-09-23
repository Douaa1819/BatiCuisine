package DAO.interfaces;

import models.Devis;

import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public interface DevisDAO {
    void save(Devis devis) throws SQLException;
    Optional<Devis> getById(UUID id) throws SQLException;

}
