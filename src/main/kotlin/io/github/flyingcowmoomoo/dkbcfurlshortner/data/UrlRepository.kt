package io.github.flyingcowmoomoo.dkbcfurlshortner.data

import io.github.flyingcowmoomoo.dkbcfurlshortner.model.Url
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface UrlRepository: CrudRepository<Url, UUID> {

    @Query("SELECT u from Url u where u.hash = :hash")
    fun getByHash(hash: String): Url?

    @Query("SELECT u from Url u where u.url = :url")
    fun getByUrl(url: String): Url?

    @Query("INSERT INTO url(url) values (:url) RETURNING id, hash, url", nativeQuery = true)
    fun create(url: String): Url
}