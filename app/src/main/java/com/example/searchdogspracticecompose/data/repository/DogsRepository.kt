package com.example.searchdogspracticecompose.data.repository

import com.example.searchdogspracticecompose.data.network.service.DogApiService
import javax.inject.Inject

class DogsRepository @Inject constructor(private val dogApiService: DogApiService) {

    suspend fun getDogsByBreeds(query: String): List<String> {
        return dogApiService.getDogsBreedsByName(query)
    }
}