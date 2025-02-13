package com.example.searchdogspracticecompose.data.repository

import com.example.searchdogspracticecompose.data.database.dao.DogsDao
import com.example.searchdogspracticecompose.data.database.entities.DogEntity
import com.example.searchdogspracticecompose.data.local.DogsBreeds
import com.example.searchdogspracticecompose.data.local.SelectBreed
import com.example.searchdogspracticecompose.data.network.service.DogApiService
import com.example.searchdogspracticecompose.domain.model.Dog
import javax.inject.Inject

class DogsRepository @Inject constructor(
    private val dogApiService: DogApiService,
    private val dogsBreeds: DogsBreeds,
    private val selectBreed: SelectBreed,
    private val dogsDao: DogsDao
) {

    suspend fun getDogsByBreeds(query: String): List<String> {
        return dogApiService.getDogsBreedsByName(query)
    }

    suspend fun getAllBreeds(): List<String> {
        if (dogsBreeds.dogBreeds.isEmpty()) {
            dogsBreeds.dogBreeds = dogApiService.getAllNamesFromApi()
        }
        return dogsBreeds.dogBreeds

    }

    fun setSelectBreed(breed: String) {
        selectBreed.selectBreed = breed
    }

    fun getSelectBreed(): String {
        return selectBreed.selectBreed!!
    }

    suspend fun getAllDogsFromDataBase(): List<DogEntity> {
        return dogsDao.getAllDogs()
    }

    suspend fun insertDogFromDataBase(dog: DogEntity) {
        dogsDao.insertDogDataBase(dog)
    }

    suspend fun deleteItemFromDB(dog: Dog) {
        dogsDao.deleteDogByUrl(dog.imageUrl)
    }

    suspend fun deleteAllItemsFromDB() {
        dogsDao.deleteAllFavoritesBreed()
    }
}