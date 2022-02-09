package br.com.paixaopet.core.business.mappers;

import br.com.paixaopet.core.business.entities.Pet;
import br.com.paixaopet.core.business.entities.PetProjection;
import br.com.paixaopet.core.entrypoint.restcontrollers.v1.requests.CreatePetRequest;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface PetMapper {

    Pet from(CreatePetRequest request);

    PetProjection from(Pet pet);
}
