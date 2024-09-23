package Services.impl;

import Repository.interfaces.DevisRepository;
import Services.interfaces.DevisService;
import models.Devis;

import java.util.Optional;
import java.util.UUID;

public class DevisServiceImpl implements DevisService {
    private final DevisRepository devisRepository;

    public DevisServiceImpl(DevisRepository devisRepository) {
        this.devisRepository = devisRepository;
    }

    @Override
    public void save(Devis devis) {
        devisRepository.save(devis);
    }

    @Override
    public Optional<Devis> getById(UUID id) {
        return devisRepository.getById(id);
    }
}
