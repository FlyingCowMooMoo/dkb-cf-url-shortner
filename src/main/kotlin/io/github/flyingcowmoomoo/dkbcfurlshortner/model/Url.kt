package io.github.flyingcowmoomoo.dkbcfurlshortner.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.util.*

@Entity
class Url(@Id @GeneratedValue val id: UUID?, val hash: String?, val url: String)
