package Repository.impl;

import DAO.impl.MainOeuvreDAOImpl;
import DAO.interfaces.MainOeuvreDAO;
import Repository.interfaces.MainOeuvreRepository;
import models.MainOeuvre;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MainOeuvreRepositoryImpl implements MainOeuvreRepository {

    private final MainOeuvreDAO mainOeuvreDAO;

    public MainOeuvreRepositoryImpl(MainOeuvreDAO mainOeuvreDAO) {
        this.mainOeuvreDAO = mainOeuvreDAO;
    }

    @Override

    public void save(MainOeuvre mainOeuvre){
        mainOeuvreDAO.create(mainOeuvre);
    }


    @Override

    public Optional<MainOeuvre>findById(UUID id){

        return mainOeuvreDAO.findById(id);
    }


    @Override
    public List<MainOeuvre> getMainOeuvreByProjetId(UUID projetId) {
        return mainOeuvreDAO.getMainOeuvreByProjetId(projetId);
    }

    @Override
    public double calculerCoutTotal(UUID projetId) {
        return mainOeuvreDAO.calculerCoutTotal(projetId);
    }

    @Override
    public double calculerCoutTotalAvantTVA(UUID projetId) {
        return mainOeuvreDAO.calculerCoutTotalAvantTVA(projetId);
    }
}
