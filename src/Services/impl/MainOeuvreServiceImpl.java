package Services.impl;


import Repository.interfaces.MainOeuvreRepository;
import Services.interfaces.MainOeuvreService;
import models.MainOeuvre;

import java.util.List;
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
    public List<MainOeuvre> getMainOeuvreByProjetId(UUID projetId) {
        return mainOeuvreRepository.getMainOeuvreByProjetId(projetId);
    }




}