package Services.impl;

import Repository.interfaces.MateriauxRepository;
import Services.interfaces.MateriauxService;
import models.Materiaux;

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
    public Optional<Materiaux> trouverMateriauxParId(UUID id) {
        return materiauxRepository.findById(id);
    }

    @Override
    public List<Materiaux> getMateriauxByProjetId(UUID projetId) {
        return materiauxRepository.getMateriauxByProjetId(projetId);
    }

    @Override
    public double calculerCoutTotal(UUID projetId) {
        return materiauxRepository.calculerCoutTotal(projetId);
    }

    @Override
    public double calculerCoutTotalAvantTVA(UUID projetId) {
        return materiauxRepository.calculerCoutTotalAvantTVA(projetId);
    }


}
