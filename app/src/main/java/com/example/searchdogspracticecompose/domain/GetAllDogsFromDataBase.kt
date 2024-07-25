package com.example.searchdogspracticecompose.domain

import com.example.searchdogspracticecompose.core.toDomain
import com.example.searchdogspracticecompose.data.repository.DogsRepository
import com.example.searchdogspracticecompose.domain.model.Dog
import javax.inject.Inject

class GetAllDogsFromDataBase @Inject constructor( private val repository: DogsRepository) {

    suspend operator fun invoke():List<Dog>{
        return repository.getAllDogsFromDataBase().map { it.toDomain() }
    }
}