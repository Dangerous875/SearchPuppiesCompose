package com.example.searchdogspracticecompose.domain

import com.example.searchdogspracticecompose.core.toDataBase
import com.example.searchdogspracticecompose.data.repository.DogsRepository
import com.example.searchdogspracticecompose.domain.model.Dog
import javax.inject.Inject

class InsertDogToDataBase @Inject constructor( private val repository: DogsRepository) {

    suspend operator fun invoke(dog: Dog){
        repository.insertDogFromDataBase(dog.toDataBase())
    }
}