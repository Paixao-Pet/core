package br.com.paixaopet.core.business;

import br.com.paixaopet.core.business.entities.PetProjection;
import br.com.paixaopet.core.business.mappers.PetMapper;
import br.com.paixaopet.core.entrypoint.restcontrollers.v1.requests.CreatePetRequest;
import br.com.paixaopet.core.repositories.PetRepository;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    private final PetMapper mapper;
    private final PetRepository repository;

    public PetService(PetMapper mapper, PetRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public PetProjection save(CreatePetRequest request) {
        var pet = mapper.from(request);

        var savedPet = repository.save(pet);

        return mapper.from(savedPet);
    }
}
