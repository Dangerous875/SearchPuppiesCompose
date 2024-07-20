package com.example.searchdogspracticecompose.data.network.service

import com.example.searchdogspracticecompose.data.network.APIService
import javax.inject.Inject

class DogApiService @Inject constructor(private val api: APIService) {

    suspend fun getDogsBreedsByName(query: String): List<String> {
        val response = api.getDogsListByName(query)
        return response.body()?.message ?: emptyList()
    }
}