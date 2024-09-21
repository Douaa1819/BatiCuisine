package DAO.interfaces;

import models.Composant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ComposantDAO {

    Optional<Composant> findById(UUID id);
    List<Composant> findAll();
    void save(Composant composant);
}
