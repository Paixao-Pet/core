package br.com.paixaopet.core.entrypoint.restcontrollers.v1

import br.com.paixaopet.core.configurations.SerializerDeserializerConfiguration
import com.fasterxml.jackson.databind.ObjectMapper
import factories.CreatePetRequestFactory.Companion.validCreatePetRequest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.spy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import utilities.FakerProvider.Companion.getFaker

@WebMvcTest(controllers = [PetsController::class, SerializerDeserializerConfiguration::class])
class PetsControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper;

    @Test
    fun `when POST to _v1_pets with valid request then receives status code 201 CREATED`() {
        mockMvc.perform(
            post(PetsController.PATH)
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(validCreatePetRequest()))
        ).andExpect(status().isCreated)
    }

    @Test
    fun `when POST to _v1_pets without request body then receives status code 400 BAD_REQUEST`() {
        mockMvc.perform(post(PetsController.PATH)).andExpect(status().isBadRequest)
    }

    @ParameterizedTest(name = "propertyName={0}, propertyValue={1}")
    @CsvSource(
        value = [
            "name,",
            "name, \"\"",
            "profile_photo,",
            "profile_photo, \"\""
        ]
    )
    fun `when POST to _v1_pets with request body with ${propertyName} equal to ${propertyValue} then receives status code 400 BAD_REQUEST`(
        propertyName: String, propertyValue: String?
    ) {
        val validCreatePetRequest = spy(validCreatePetRequest())

        val jsonContent = mapper.writeValueAsString(validCreatePetRequest).replace(
            "\"${propertyName}\":\".*\",".toRegex(),
            "\"${propertyName}\":${propertyValue},"
        )

        mockMvc.perform(
            post(PetsController.PATH).contentType(APPLICATION_JSON).content(jsonContent)
        ).andExpect(status().isBadRequest)
    }

    @ParameterizedTest(name = "propertyName={0}")
    @ValueSource(
        strings = ["specie", "gender", "castrated"]
    )
    fun `when POST to _v1_pets with request body with ${propertyName} equal to null then receives status code 400 BAD_REQUEST`(
        propertyName: String
    ) {
        val validCreatePetRequest = spy(validCreatePetRequest())

        val jsonContent = mapper.writeValueAsString(validCreatePetRequest).replace(
            "\"name\":([a-z|A-Z]*|\".*\"|[0-9]*),".toRegex(),
            "\"${propertyName}\":null,\""
        )

        mockMvc.perform(
            post(PetsController.PATH).contentType(APPLICATION_JSON).content(jsonContent)
        ).andExpect(status().isBadRequest)
    }

    @Test
    fun `when POST to _v1_pets with request body with approximatedAge containing value less than 0_1 then receives status code 400 BAD_REQUEST`() {
        val validCreatePetRequest = spy(validCreatePetRequest())

        doReturn(getFaker().random().nextInt(1, 9) / 100F).`when`(validCreatePetRequest).approximateAge

        val jsonContent = mapper.writeValueAsString(validCreatePetRequest)

        mockMvc.perform(
            post(PetsController.PATH).contentType(APPLICATION_JSON).content(jsonContent)
        ).andExpect(status().isBadRequest)
    }

    @Test
    fun `when POST to _v1_pets with request body without approximatedAge and birthDate then receives status code 400 BAD_REQUEST`() {
        val validCreatePetRequest = spy(validCreatePetRequest())

        doReturn(null).`when`(validCreatePetRequest).birthDate
        doReturn(null).`when`(validCreatePetRequest).approximateAge

        val jsonContent = mapper.writeValueAsString(validCreatePetRequest)

        mockMvc.perform(
            post(PetsController.PATH).contentType(APPLICATION_JSON).content(jsonContent)
        ).andExpect(status().isBadRequest)
    }

    @Test
    fun `when POST to _v1_pets with request body without approximatedAge but has birthDate then receives status code 201 CREATED`() {
        val validCreatePetRequest = spy(validCreatePetRequest())

        doReturn(null).`when`(validCreatePetRequest).approximateAge

        val jsonContent = mapper.writeValueAsString(validCreatePetRequest)

        mockMvc.perform(
            post(PetsController.PATH).contentType(APPLICATION_JSON).content(jsonContent)
        ).andExpect(status().isCreated)
    }

    @Test
    fun `when POST to _v1_pets with request body without birthDate but has approximatedAge then receives status code 201 CREATED`() {
        val validCreatePetRequest = spy(validCreatePetRequest())

        doReturn(null).`when`(validCreatePetRequest).birthDate

        val jsonContent = mapper.writeValueAsString(validCreatePetRequest)

        mockMvc.perform(
            post(PetsController.PATH).contentType(APPLICATION_JSON).content(jsonContent)
        ).andExpect(status().isCreated)
    }
}