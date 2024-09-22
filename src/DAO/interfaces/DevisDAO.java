package DAO.interfaces;

import models.Devis;

import java.sql.SQLException;
import java.util.UUID;

public interface DevisDAO {
    void save(Devis devis) throws SQLException;
    Devis getById(UUID id) throws SQLException;
}
