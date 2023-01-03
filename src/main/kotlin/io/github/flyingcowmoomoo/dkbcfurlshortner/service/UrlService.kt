package io.github.flyingcowmoomoo.dkbcfurlshortner.service

import io.github.flyingcowmoomoo.dkbcfurlshortner.data.UrlRepository
import io.github.flyingcowmoomoo.dkbcfurlshortner.model.Url
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UrlService @Autowired constructor (private val repo: UrlRepository) {
    @Transactional
    fun createOrGetHash(url: String): Url {
        return repo.getByUrl(url) ?: repo.create(url)
    }

    fun getByHash(hash: String): Url? {
        return repo.getByHash(hash)
    }
}