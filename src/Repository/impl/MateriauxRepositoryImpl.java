package Repository.impl;

import DAO.impl.MateriauxDAOImpl;
import DAO.interfaces.MateriauxDAO;
import Repository.interfaces.MateriauxRepository;
import models.Materiaux;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MateriauxRepositoryImpl implements MateriauxRepository {

    private  final MateriauxDAO materiauxDAO;

    public MateriauxRepositoryImpl(MateriauxDAO materiauxDAO) {
        this.materiauxDAO = materiauxDAO;
    }


    @Override

    public void save(Materiaux materiaux){

        materiauxDAO.create(materiaux);
    }




    @Override
    public List<Materiaux> getMateriauxByProjetId(UUID projetId) {
        return materiauxDAO.getMateriauxByProjetId(projetId);
    }


}
