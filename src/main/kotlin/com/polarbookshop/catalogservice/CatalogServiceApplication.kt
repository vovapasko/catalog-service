package com.polarbookshop.catalogservice

import com.polarbookshop.catalogservice.config.PolarProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ConfigurationPropertiesScan
class CatalogServiceApplication

fun main(args: Array<String>) {
    runApplication<CatalogServiceApplication>(*args)
}
