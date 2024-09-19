package DAO.interfaces;

import models.Projet;

import java.util.List;
import java.util.UUID;

public interface ProjetDAO {

    void save(Projet projet);
    List<Projet> findAll();
//    Projet findById(UUID id);
}
