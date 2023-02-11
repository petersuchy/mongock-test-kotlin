package com.example.mongocktestkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@EnableReactiveMongoRepositories
@SpringBootApplication
class MongockTestKotlinApplication

fun main(args: Array<String>) {
    runApplication<MongockTestKotlinApplication>(*args)
}
