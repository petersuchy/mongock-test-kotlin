package com.example.mongocktestkotlin.config.db.migration

import com.example.mongocktestkotlin.repository.ClientRepository
import com.example.mongocktestkotlin.repository.entity.Client
import com.mongodb.reactivestreams.client.MongoCollection
import io.mongock.api.annotations.BeforeExecution
import io.mongock.api.annotations.ChangeUnit
import io.mongock.api.annotations.Execution
import io.mongock.driver.mongodb.reactive.util.MongoSubscriberSync
import io.mongock.driver.mongodb.reactive.util.SubscriberSync
import org.bson.Document
import org.slf4j.LoggerFactory
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import reactor.core.publisher.Flux

@ChangeUnit(id = "client-initializer", order = "1", author = "mongock")
class ClientInitializerChangeUnit {

    private val logger = LoggerFactory.getLogger(javaClass)

    private val collectionName = "clientCollection"


    @BeforeExecution
    fun beforeExecution(mongoTemplate: ReactiveMongoTemplate) {
        val subscriber: SubscriberSync<MongoCollection<Document>> = MongoSubscriberSync()

        mongoTemplate.createCollection(collectionName).subscribe(subscriber)
        subscriber.await()
        logger.info("ClientInitializerChangeLog.beforeExecution: Finished")
    }

    @Execution
    fun execution(clientRepository: ClientRepository) {
        val subscriber: SubscriberSync<Client> = MongoSubscriberSync()

        Flux.fromIterable((1..5).map { createClient(it) })
            .flatMap { clientRepository.save(it) }
            .subscribe(subscriber)

        subscriber.get().forEach { logger.info("Client inserted Successfully: $it") }


    }

    private fun createClient(number: Int): Client =
        Client("name-$number", "email-$number", "phone-$number", "country-$number")

}