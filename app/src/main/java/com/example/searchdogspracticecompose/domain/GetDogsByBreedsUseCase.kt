package com.example.searchdogspracticecompose.domain

import com.example.searchdogspracticecompose.data.repository.DogsRepository
import javax.inject.Inject

class GetDogsByBreedsUseCase @Inject constructor(private val dogsRepository: DogsRepository) {

    suspend operator fun invoke(query:String):List<String>{
        return dogsRepository.getDogsByBreeds(query)
    }
}