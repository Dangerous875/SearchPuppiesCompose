package com.example.searchdogspracticecompose.domain

import com.example.searchdogspracticecompose.core.toDomain
import com.example.searchdogspracticecompose.data.repository.DogsRepository
import com.example.searchdogspracticecompose.domain.model.Dog
import javax.inject.Inject

class GetDogsByBreedsUseCase @Inject constructor(private val dogsRepository: DogsRepository) {

    suspend operator fun invoke(query: String): List<Dog> {
        val list = dogsRepository.getDogsByBreeds(query)
        val listDogsByName = list.map { it.toDomain() }
        return checkIfExistInBD(listDogsByName)
    }

    private suspend fun checkIfExistInBD(listDogsByName: List<Dog>): List<Dog> {
        val dogsInFavorite = dogsRepository.getAllDogsFromDataBase().map { it.toDomain() }
        val favoriteUrls = dogsInFavorite.map { it.imageUrl }.toSet()

        return listDogsByName.map { dog ->
            if (favoriteUrls.contains(dog.imageUrl)) {
                dog.copy(iconFavorite = true)
            } else {
                dog
            }
        }
    }
}