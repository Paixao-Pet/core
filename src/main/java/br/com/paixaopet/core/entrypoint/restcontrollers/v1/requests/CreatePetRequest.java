package br.com.paixaopet.core.entrypoint.restcontrollers.v1.requests;

import br.com.paixaopet.core.business.entities.Gender;
import br.com.paixaopet.core.business.entities.Specie;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public record CreatePetRequest(
        @NotBlank String name,
        @NotBlank String profilePhoto,
        List<String> photos,
        @NotNull Specie specie,
        @NotNull Gender gender,
        @NotNull Boolean castrated,
        LocalDate birthDate,
        @DecimalMin("0.1") Float approximateAge,
        List<String> specialCares
) {

    @JsonIgnore
    @AssertFalse
    public boolean isBirthDateAndApproximateDateEqualToNull() {
        return approximateAge == null && birthDate == null;
    }
}
