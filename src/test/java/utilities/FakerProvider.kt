package utilities

import com.github.javafaker.Faker
import java.time.ZoneId.systemDefault
import java.time.ZonedDateTime
import java.util.*
import java.util.concurrent.TimeUnit.DAYS

class FakerProvider private constructor() {

    companion object {
        private var fakerInstance: Faker = Faker(Locale("pt-BR"))
        private const val YEAR_IN_DAYS: Int = 365;

        fun getFaker(): Faker {
            return fakerInstance;
        }

        fun pastZonedDateTime(maxYears: Int): ZonedDateTime {
            val yearsToDays = maxYears * YEAR_IN_DAYS
            return fakerInstance.date().past(yearsToDays, DAYS).toInstant().atZone(systemDefault());
        }

        fun futureZonedDateTime(maxYears: Int): ZonedDateTime {
            val yearsToDays = maxYears * YEAR_IN_DAYS
            return fakerInstance.date().future(yearsToDays, DAYS).toInstant().atZone(systemDefault());
        }
    }
}