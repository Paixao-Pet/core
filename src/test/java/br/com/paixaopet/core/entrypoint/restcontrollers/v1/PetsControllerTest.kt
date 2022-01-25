package br.com.paixaopet.core.entrypoint.restcontrollers.v1

import br.com.paixaopet.core.configurations.SerializerDeserializerConfiguration
import br.com.paixaopet.core.entrypoint.restcontrollers.v1.requests.CreatePetRequest
import com.fasterxml.jackson.databind.ObjectMapper
import factories.CreatePetRequestFactory.Companion.validCreatePetRequest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.mockito.Mockito.spy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

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
}