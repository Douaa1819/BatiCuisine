package Repository.impl;


import DAO.interfaces.DevisDAO;
import Repository.interfaces.DevisRepository;
import models.Devis;

import java.sql.SQLException;
import java.util.Optional;
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
    public Optional<Devis> findByProjetId(UUID projetId) throws SQLException {
        return devisDAO.getDevisByProjetId(projetId);
    }
}
