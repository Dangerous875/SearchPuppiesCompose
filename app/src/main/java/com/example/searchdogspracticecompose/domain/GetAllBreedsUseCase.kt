package com.example.searchdogspracticecompose.domain

import com.example.searchdogspracticecompose.data.repository.DogsRepository
import javax.inject.Inject

class GetAllBreedsUseCase @Inject constructor(private val dogsRepository: DogsRepository) {

    suspend operator fun invoke(): List<String> {
        return dogsRepository.getAllBreeds()
    }
}