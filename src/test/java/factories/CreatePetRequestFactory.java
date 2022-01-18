package factories;

import br.com.paixaopet.core.business.entities.Gender;
import br.com.paixaopet.core.business.entities.Specie;
import br.com.paixaopet.core.entrypoint.restcontrollers.v1.requests.CreatePetRequest;

import java.time.LocalDate;
import java.util.List;

public final class CreatePetRequestFactory {

    private CreatePetRequestFactory() {
    }

    public static CreatePetRequest valid() {
        return new CreatePetRequest(
                "name",
                "any",
                List.of(),
                Specie.CAT,
                Gender.FEMALE,
                false,
                LocalDate.now(),
                0.2F,
                List.of()
        );
    }
}
