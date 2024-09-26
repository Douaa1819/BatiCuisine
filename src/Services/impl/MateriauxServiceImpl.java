package Services.impl;

import Repository.interfaces.MateriauxRepository;
import Services.interfaces.MateriauxService;
import models.Composant;
import models.Materiaux;
import models.Projet;

import java.sql.SQLException;
import java.util.List;
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
    public List<Materiaux> getMateriauxByProjetId(UUID projetId) {
        return materiauxRepository.getMateriauxByProjetId(projetId);
    }

    public List <String> getNomComposant(Projet projet) throws SQLException {

        List <Composant> nomComposant = materiauxRepository.getMateriauxByProjetId(UUID projet);
        projets.stream().map(p->p).


    }



}
