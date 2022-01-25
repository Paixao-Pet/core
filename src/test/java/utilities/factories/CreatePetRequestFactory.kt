package utilities.factories

import br.com.paixaopet.core.business.entities.Gender
import br.com.paixaopet.core.business.entities.Specie
import br.com.paixaopet.core.entrypoint.restcontrollers.v1.requests.CreatePetRequest
import utilities.EnumRandom.Companion.anyEnumOf
import utilities.FakerProvider.Companion.getFaker
import java.time.ZoneId.systemDefault
import java.util.*

class CreatePetRequestFactory private constructor() {

    companion object {
        fun validCreatePetRequest(): CreatePetRequest {
            val faker = getFaker()

            val profilePhoto = Base64.getEncoder().encodeToString(
                faker.bothify("???").toByteArray()
            )
            val birthDate = faker.date().birthday().toInstant().atZone(systemDefault()).toLocalDate()

            val approximateAge = faker.random().nextInt(10, 150) / 100F;

            return CreatePetRequest(
                faker.name().firstName(),
                profilePhoto,
                setOf(),
                anyEnumOf(Specie::class.java),
                anyEnumOf(Gender::class.java),
                faker.bool().bool(),
                birthDate,
                approximateAge,
                setOf()
            )
        }
    }
}