package com.example.mongocktestkotlin.repository.entity

import org.bson.BsonType
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonRepresentation
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Field
import java.time.LocalDateTime

class Client(
    @BsonId
    @BsonRepresentation(value = BsonType.OBJECT_ID)
    val id: String,

    @Field("name")
    val name: String,

    @Field
    val dateTime: LocalDateTime,

    @Field("email")
    val email: String,

    @Field("phone")
    val phone: String,

    @Field("country")
    val country: String
) {
    constructor(name: String, email: String, phone: String, country: String) :
            this(ObjectId.get().toString(), name, LocalDateTime.now(), email, phone, country)
}
