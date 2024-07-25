package com.example.searchdogspracticecompose.domain

import com.example.searchdogspracticecompose.data.repository.DogsRepository
import com.example.searchdogspracticecompose.domain.model.Dog
import javax.inject.Inject

class DeleteDogFromDataBase @Inject constructor(private val dogsRepository: DogsRepository) {

    suspend operator fun invoke(dog: Dog){
        dogsRepository.deleteItemFromDB(dog)
    }
}