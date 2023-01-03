package io.github.flyingcowmoomoo.dkbcfurlshortner.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.github.flyingcowmoomoo.dkbcfurlshortner.data.UrlRepository
import io.github.flyingcowmoomoo.dkbcfurlshortner.model.UrlRequest
import io.github.flyingcowmoomoo.dkbcfurlshortner.model.UrlResponse
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.testcontainers.junit.jupiter.Testcontainers

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("test-containers")
class UrlControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var repo: UrlRepository

    @Test
    fun `test GET with a hash where the entry exists in the database`() {
        val responseBody = mockMvc.perform(
            MockMvcRequestBuilders.get("/FFQw4w9WgXcQ")
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
            .andReturn().response.contentAsString
        assertEquals("https://www.bbc.com/news/world-europe-64144309", responseBody)
    }

    @Test
    fun `test GET with a hash where the entry does not exist in the database`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/IShouldNotBeHere")
        ).andExpect(MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    fun `test POST with a new Url`() {
        val mapper = jacksonObjectMapper()
        val body = mapper.writeValueAsString(UrlRequest("https://some.site/1/2/3"))
        val responseBody =
            mockMvc.perform(MockMvcRequestBuilders.post("/").contentType(APPLICATION_JSON_VALUE).content(body))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful).andReturn().response.contentAsString
        val response: UrlResponse = mapper.readValue(responseBody)
        val expectedHash = repo.getByUrl("https://some.site/1/2/3")!!.hash
        assertEquals(UrlResponse(expectedHash), response)
    }

    @Test
    fun `test POST with an existing Url`() {
        val mapper = jacksonObjectMapper()
        val body = mapper.writeValueAsString(UrlRequest("https://www.youtube.com/watch?v=dQw4w9WgXcQ"))
        val responseBody =
            mockMvc.perform(MockMvcRequestBuilders.post("/").contentType(APPLICATION_JSON_VALUE).content(body))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful).andReturn().response.contentAsString
        val response: UrlResponse = mapper.readValue(responseBody)
        assertEquals("dQw4w9WgXcQ", response.hash?.trim())
    }
}