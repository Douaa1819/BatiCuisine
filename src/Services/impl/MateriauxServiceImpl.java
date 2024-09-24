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
    public List<Materiaux> getMateriauxByProjetId(UUID projetId) {
        return materiauxRepository.getMateriauxByProjetId(projetId);
    }




}
