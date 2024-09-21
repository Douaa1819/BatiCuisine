package Services.interfaces;

import Repository.impl.ProjetRepositoryImpl;
import Repository.interfaces.ProjetRepository;
import models.Projet;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjetService {


    void ajouterProjet(Projet projet) throws SQLException;
    Optional<Projet> getProjetById(UUID id) throws SQLException;
    List<Projet> getAllProjets() throws SQLException;
    void mettreAJourProjet(Projet projet) throws SQLException;
}
