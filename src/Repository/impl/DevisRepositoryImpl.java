package Repository.impl;


import DAO.interfaces.DevisDAO;
import Repository.interfaces.DevisRepository;
import models.Devis;

import java.sql.SQLException;
import java.util.UUID;

public class DevisRepositoryImpl implements DevisRepository {
    private final DevisDAO devisDAO;

    public DevisRepositoryImpl(DevisDAO devisDAO) {
        this.devisDAO = devisDAO;
    }

    @Override
    public void save(Devis devis) {
        try {
            devisDAO.save(devis);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Devis getById(UUID id) {
        try {
            return devisDAO.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
