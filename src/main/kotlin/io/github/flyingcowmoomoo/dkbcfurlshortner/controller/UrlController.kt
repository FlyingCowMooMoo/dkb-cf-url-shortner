package io.github.flyingcowmoomoo.dkbcfurlshortner.controller

import io.github.flyingcowmoomoo.dkbcfurlshortner.model.UrlRequest
import io.github.flyingcowmoomoo.dkbcfurlshortner.model.UrlResponse
import io.github.flyingcowmoomoo.dkbcfurlshortner.service.UrlService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
class UrlController @Autowired constructor (private val service: UrlService) {

    @GetMapping("/{hash}")
    fun getUrlByHash(@PathVariable hash: String): String {
        return service.getByHash(hash)?.url ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "No result found")
    }

    @PostMapping("/")
    fun createUrl(@RequestBody request: UrlRequest): UrlResponse {
        return UrlResponse(service.createOrGetHash(request.url).hash)
    }
}