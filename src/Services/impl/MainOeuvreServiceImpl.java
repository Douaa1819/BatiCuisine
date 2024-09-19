package Services.impl;

import DAO.impl.MainOeuvreDAOImpl;
import DAO.interfaces.MainOeuvreDAO;
import DAO.interfaces.MateriauxDAO;
import Repository.impl.MainOeuvreRepositoryImpl;
import Repository.interfaces.MainOeuvreRepository;
import Services.interfaces.MainOeuvreService;
import models.MainOeuvre;

import java.util.Optional;
import java.util.UUID;

public class MainOeuvreServiceImpl implements MainOeuvreService {

    private final MainOeuvreRepository mainOeuvreRepository;

    public MainOeuvreServiceImpl(MainOeuvreRepository mainOeuvreRepository) {
        this.mainOeuvreRepository = mainOeuvreRepository;
    }

    @Override
    public void ajouterMainOeuvre(MainOeuvre mainOeuvre) {
        mainOeuvreRepository.save(mainOeuvre);
    }

    @Override
    public Optional<MainOeuvre> trouverMainOeuvreParId(UUID id) {
        return mainOeuvreRepository.findById(id);
    }
}