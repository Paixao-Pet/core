package br.com.paixaopet.core.repositories;

import br.com.paixaopet.core.business.entities.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
