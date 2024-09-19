package Services.impl;

import DAO.impl.MateriauxDAOImpl;
import DAO.interfaces.MateriauxDAO;
import Repository.impl.MateriauxRepositoryImpl;
import Repository.interfaces.MateriauxRepository;
import Services.interfaces.MateriauxService;
import models.Materiaux;

import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class MateriauxServiceImpl implements MateriauxService {

    private final MateriauxRepository materiauxRepository ;

    public MateriauxServiceImpl(MateriauxRepository materiauxRepository) {
        this.materiauxRepository = materiauxRepository;
    }

    @Override
    public void ajouterMateriaux(Materiaux materiaux)  {
        materiauxRepository.save(materiaux);
    }

    @Override
    public Optional<Materiaux> trouverMateriauxParId(UUID id) {
        return materiauxRepository.findById(id);
    }
}