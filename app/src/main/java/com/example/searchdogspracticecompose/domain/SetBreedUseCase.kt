package com.example.searchdogspracticecompose.domain

import com.example.searchdogspracticecompose.data.repository.DogsRepository
import javax.inject.Inject

class SetBreedUseCase @Inject constructor(private val dogsRepository: DogsRepository) {

    operator fun invoke(breed: String) {
        dogsRepository.setSelectBreed(breed)
    }
}