package Repository.impl;

import DAO.impl.ProjetDAOImpl;
import DAO.interfaces.ProjetDAO;
import Repository.interfaces.ProjetRepository;
import models.Projet;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProjetRepositoryImpl implements ProjetRepository {
    private final ProjetDAO projetDAO;

    public ProjetRepositoryImpl() {
        this.projetDAO = new ProjetDAOImpl();     }

    @Override
    public void save(Projet projet) throws SQLException {
        projetDAO.save(projet);
    }

    @Override
    public Optional<Projet> findById(UUID id) throws SQLException {
        return projetDAO.getProjetById(id);
    }

    @Override
    public List<Projet> findAll() throws SQLException {
        return projetDAO.getAllProjets();
    }
}
