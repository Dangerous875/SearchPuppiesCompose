package com.example.searchdogspracticecompose.data.repository

import com.example.searchdogspracticecompose.data.local.DogsBreeds
import com.example.searchdogspracticecompose.data.local.SelectBreed
import com.example.searchdogspracticecompose.data.network.service.DogApiService
import javax.inject.Inject

class DogsRepository @Inject constructor(
    private val dogApiService: DogApiService,
    private val dogsBreeds: DogsBreeds,
    private val selectBreed: SelectBreed
) {

    suspend fun getDogsByBreeds(query: String): List<String> {
        return dogApiService.getDogsBreedsByName(query)
    }

    fun getAllBreeds(): List<String> {
        return dogsBreeds.dogBreeds
    }

    fun setSelectBreed(breed: String) {
        selectBreed.selectBreed = breed
    }

    fun getSelectBreed():String{
        return selectBreed.selectBreed!!
    }
}