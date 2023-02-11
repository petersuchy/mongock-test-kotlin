package com.example.mongocktestkotlin.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.bson.codecs.configuration.CodecRegistries
import org.bson.codecs.pojo.PojoCodecProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean

class MongoClientConfig(
    @Value("\${spring.data.mongodb.uri}") val mongodbUri: String
) {


    @Bean
    fun mongoClient(): MongoClient {
        val codecRegistry = CodecRegistries.fromRegistries(
            MongoClientSettings.getDefaultCodecRegistry(),
            CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
        )

        return MongoClients.create(
            MongoClientSettings.builder()
                .applyConnectionString(ConnectionString(mongodbUri))
                .codecRegistry(codecRegistry)
                .build()
        )
    }
}
