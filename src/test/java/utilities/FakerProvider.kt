package utilities

import com.github.javafaker.Faker
import java.util.*

class FakerProvider private constructor(){

    companion object {
        private var fakerInstance: Faker = Faker(Locale("pt-BR"))

        fun getFaker(): Faker {
            return fakerInstance;
        }
    }
}