package com.acme.firstspringkotlin.controller

import com.acme.firstspringkotlin.loadFile
import com.acme.firstspringkotlin.repository.TransferRepository
import com.acme.firstspringkotlin.repository.model.TransactionDBModel
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.Date
import java.util.UUID

@WebMvcTest
class TransferControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var paymentsRepository: TransferRepository

    @Test
    fun `should submit transaction with success`() {

        every { paymentsRepository.save(any()) } returns mockk()

        mockMvc.perform(
            MockMvcRequestBuilders.post("/transfers")
            .content(validPersistRequest)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

/*    @Test
    fun `should get transaction with success`() {
        val mockTransaction = mockk<TransactionDBModel>().apply {
            every { amount } returns 1.50
            every { description } returns "store 1"
            every { accountNumber } returns "NL47INGB8845464385"
            every { date } returns Date()
            every { id } returns UUID.randomUUID()
        }

        every { paymentsRepository.findAll() } returns mutableListOf(mockTransaction)

        mockMvc.perform(MockMvcRequestBuilders.get("/transfers").accept(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.[0].description").value("store 1"))
    }
*/
    @Test
    fun `should return a bad request error if the request there's no body`() {
        mockMvc.perform(MockMvcRequestBuilders.post("/transfers")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().is4xxClientError)
    }

    companion object {
        private val validPersistRequest = loadFile("fixtures/validCreateRequest.json")
    }

}
