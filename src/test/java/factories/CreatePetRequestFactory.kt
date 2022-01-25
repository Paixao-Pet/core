package factories

import br.com.paixaopet.core.business.entities.Gender
import br.com.paixaopet.core.business.entities.Specie
import br.com.paixaopet.core.entrypoint.restcontrollers.v1.requests.CreatePetRequest
import com.github.javafaker.Faker
import java.security.SecureRandom
import java.time.ZoneId
import java.time.ZoneId.systemDefault
import java.util.*

class CreatePetRequestFactory private constructor() {

    companion object {
        private var random: SecureRandom = SecureRandom()

        var faker: Faker = Faker(Locale("pt-BR"))

        fun validCreatePetRequest(): CreatePetRequest {
            val profilePhoto = Base64.getEncoder().encodeToString(
                faker.bothify("???").toByteArray()
            )
            val birthDate = faker.date().birthday().toInstant().atZone(systemDefault()).toLocalDate()

            val approximateAge = faker.random().nextInt(10, 150) / 100F;

            return CreatePetRequest(
                faker.name().firstName(),
                profilePhoto,
                listOf(),
                anyEnumOf(Specie::class.java),
                anyEnumOf(Gender::class.java),
                faker.bool().bool(),
                birthDate,
                approximateAge,
                listOf()
            )
        }

        fun <T : Enum<*>?> anyEnumOf(clazz: Class<T>): T {
            val x: Int = random.nextInt(clazz.enumConstants.size)
            return clazz.enumConstants[x]
        }
    }
}