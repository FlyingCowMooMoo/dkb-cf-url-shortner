package io.github.flyingcowmoomoo.dkbcfurlshortner

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EntityScan("io.github.flyingcowmoomoo.*")
@EnableJpaRepositories("io.github.flyingcowmoomoo.*")
class DkbCfUrlShortnerApplication

fun main(args: Array<String>) {
    runApplication<DkbCfUrlShortnerApplication>(*args)
}
