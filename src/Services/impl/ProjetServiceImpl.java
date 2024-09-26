package Services.impl;

import Repository.impl.ProjetRepositoryImpl;
import Repository.interfaces.ProjetRepository;
import Services.interfaces.ProjetService;
import models.Composant;
import models.Projet;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProjetServiceImpl implements ProjetService {
    private final ProjetRepository projetRepository;

    public ProjetServiceImpl() {
        this.projetRepository = new ProjetRepositoryImpl();
    }

    @Override
    public void ajouterProjet(Projet projet) throws SQLException {
        projetRepository.save(projet);
    }

    @Override
    public Optional<Projet> getProjetById(UUID id) throws SQLException {
        return projetRepository.findById(id);
    }

    @Override
    public List<Projet> getAllProjets() throws SQLException {
        return projetRepository.findAll();
    }

    public List <String> getNomComposant(Projet projet) throws SQLException {

        List <Composant> nomComposant =;
     projets.stream().map(p->p).


    }



    @Override
    public void mettreAJourProjet(Projet projet) throws SQLException {
        projetRepository.update(projet);
    }
}
