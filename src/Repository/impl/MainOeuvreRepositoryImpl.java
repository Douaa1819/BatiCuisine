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
    public List<MainOeuvre> getMainOeuvreByProjetId(UUID projetId) {
        return mainOeuvreDAO.getMainOeuvreByProjetId(projetId);
    }


}
