package io.github.flyingcowmoomoo.dkbcfurlshortner.service

import io.github.flyingcowmoomoo.dkbcfurlshortner.data.UrlRepository
import io.github.flyingcowmoomoo.dkbcfurlshortner.model.Url
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.junit.jupiter.Testcontainers
import java.util.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
@ActiveProfiles("test-containers")
class UrlServiceTest {

    @Mock
    private val repo: UrlRepository? = null

    @Test
    fun `test createOrGetHash where the repo has an existing entry`() {
        val url = "https://some.site/1/2/3"
        Mockito.`when`(repo!!.getByUrl(url)).thenReturn(Url(UUID.randomUUID(), "hashString", url))
        val result = UrlService(repo).createOrGetHash(url)
        assertEquals("hashString", result.hash)
        assertEquals(url, result.url)
    }

    @Test
    fun `test createOrGetHash where the repo does not have an existing entry`() {
        val url = "https://some.site/1/2/4"
        Mockito.`when`(repo!!.getByUrl(url)).thenReturn(null)
        Mockito.`when`(repo.create(url)).thenReturn(Url(UUID.randomUUID(), "hashString2", url))
        val result = UrlService(repo).createOrGetHash(url)
        assertEquals("hashString2", result.hash)
        assertEquals(url, result.url)
    }

    @Test
    fun `test getByHash`() {
        val url = "https://some.site/1/2/5"
        Mockito.`when`(repo!!.getByHash("hashString")).thenReturn(Url(UUID.randomUUID(), "hashString", url))
        val result = UrlService(repo).getByHash("hashString")
        assertEquals("hashString", result?.hash)
        assertEquals(url, result?.url)

    }
}