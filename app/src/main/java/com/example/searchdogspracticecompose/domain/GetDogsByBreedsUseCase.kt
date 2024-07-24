package com.example.searchdogspracticecompose.domain

import com.example.searchdogspracticecompose.core.toDomain
import com.example.searchdogspracticecompose.data.repository.DogsRepository
import com.example.searchdogspracticecompose.domain.model.Dog
import javax.inject.Inject

class GetDogsByBreedsUseCase @Inject constructor(private val dogsRepository: DogsRepository) {

    suspend operator fun invoke(query: String): List<Dog> {
        val list = dogsRepository.getDogsByBreeds(query)
        return list.map { it.toDomain() }
    }
}