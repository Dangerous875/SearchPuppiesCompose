package com.example.searchdogspracticecompose.core

import com.example.searchdogspracticecompose.data.database.entities.DogEntity
import com.example.searchdogspracticecompose.domain.model.Dog

fun String.toDomain() =  Dog(this, false)

fun DogEntity.toDomain() = Dog(this.imageUrl,this.iconFavorite)

fun Dog.toDataBase() = DogEntity(imageUrl = this.imageUrl , iconFavorite = this.iconFavorite)
