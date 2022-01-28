package utilities

import utilities.factories.CreatePetRequestFactory
import java.security.SecureRandom

class EnumRandom private constructor(){

    companion object {
        private var random: SecureRandom = SecureRandom()

        fun <T : Enum<*>?> anyEnumOf(clazz: Class<T>): T {
            val x: Int = random.nextInt(clazz.enumConstants.size)
            return clazz.enumConstants[x]
        }
    }
}