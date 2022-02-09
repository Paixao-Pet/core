package br.com.paixaopet.core.business.entities;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record PetProjection(
        UUID identifier,
        String name,
        String profilePhoto,
        Set<String> photos,
        Specie specie,
        Gender gender,
        Boolean castrated,
        LocalDate birthDate,
        Float approximateAge,
        Set<String> specialCares
) {
}
