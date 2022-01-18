package br.com.paixaopet.core.entrypoint.restcontrollers.v1

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import factories.CreatePetRequestFactory
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [PetsController::class])
class PetsControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    companion object {
        private val mapper = ObjectMapper()

        @BeforeAll
        @JvmStatic
        internal fun beforeAll() {
            mapper.registerModule(JavaTimeModule())
        }
    }

    @Test
    fun `when POST to _v1_pets with valid request then receives status code 201 CREATED`() {
        mockMvc.perform(
                post(PetsController.PATH)
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(CreatePetRequestFactory.valid()))
        ).andExpect(status().isCreated)
    }
}