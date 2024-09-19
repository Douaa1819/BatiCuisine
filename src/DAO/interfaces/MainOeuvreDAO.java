package DAO.interfaces;

import models.MainOeuvre;

import java.util.Optional;
import java.util.UUID;

public interface MainOeuvreDAO {
    void create(MainOeuvre mainOeuvre);
    Optional<MainOeuvre> findById(UUID id);
}
