package br.com.paixaopet.core.entrypoint.restcontrollers.v1.requests;

import br.com.paixaopet.core.business.entities.Gender;
import br.com.paixaopet.core.business.entities.Specie;
import br.com.paixaopet.core.utilities.ValidationPatterns;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Set;

public record CreatePetRequest(
        @NotBlank String name,
        @NotBlank @Pattern(regexp = ValidationPatterns.BASE64) String profilePhoto,
        Set<@NotBlank @Pattern(regexp = ValidationPatterns.BASE64) String> photos,
        @NotNull Specie specie,
        @NotNull Gender gender,
        @NotNull Boolean castrated,
        @Past LocalDate birthDate,
        @DecimalMin("0.1") Float approximateAge,
        Set<@NotBlank String> specialCares
) {

    @JsonIgnore
    @AssertFalse
    public boolean isBirthDateAndApproximateDateEqualToNull() {
        return approximateAge == null && birthDate == null;
    }
}
