package com.example.mongocktestkotlin.config

import com.mongodb.reactivestreams.client.MongoClient
import io.mongock.driver.mongodb.reactive.driver.MongoReactiveDriver
import io.mongock.runner.springboot.MongockSpringboot
import io.mongock.runner.springboot.base.MongockInitializingBeanRunner
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class MongockConfig(
    @Value("\${spring.data.mongodb.database}") val mongoDatabaseName: String,
    @Value("\${mongock.migration-scan-package}") val mongockMigrationScanPackage: String,
    @Value("\${mongock.transaction-enabled}") val mongockTransactionEnabled: Boolean
) {


    @Bean
    fun getBuilder(reactiveMongoClient: MongoClient, context: ApplicationContext): MongockInitializingBeanRunner {
        return MongockSpringboot.builder()
            .setDriver(MongoReactiveDriver.withDefaultLock(reactiveMongoClient, mongoDatabaseName))
            .addMigrationScanPackage(mongockMigrationScanPackage)
            .setSpringContext(context)
            .setTransactionEnabled(mongockTransactionEnabled)
            .buildInitializingBeanRunner()
    }
}
