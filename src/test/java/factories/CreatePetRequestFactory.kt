package factories

import br.com.paixaopet.core.business.entities.Gender
import br.com.paixaopet.core.business.entities.Specie
import br.com.paixaopet.core.entrypoint.restcontrollers.v1.requests.CreatePetRequest
import java.time.LocalDate

class CreatePetRequestFactory private constructor() {

    companion object {
        fun validCreatePetRequest(): CreatePetRequest {
            return CreatePetRequest(
                "name",
                "any",
                listOf(),
                Specie.CAT,
                Gender.FEMALE,
                false,
                LocalDate.now(),
                0.2f,
                listOf()
            );
        }
    }
}