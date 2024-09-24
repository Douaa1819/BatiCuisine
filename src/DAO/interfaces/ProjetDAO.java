package DAO.interfaces;

import models.Projet;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjetDAO {

    void save(Projet projet) throws SQLException;
    Optional<Projet> getProjetById(UUID id) throws SQLException;
    List<Projet> getAllProjets() throws SQLException;
    void update(Projet projet) throws SQLException;
}
