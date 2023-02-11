package com.example.mongocktestkotlin.repository

import com.example.mongocktestkotlin.repository.entity.Client
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ClientRepository : ReactiveMongoRepository<Client, String>
