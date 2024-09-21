package Repository.interfaces;

import models.Projet;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjetRepository {
    void save(Projet projet) throws SQLException;
    Optional<Projet> findById(UUID id) throws SQLException;
    List<Projet> findAll() throws SQLException;
    void update(Projet projet) throws SQLException;
}
