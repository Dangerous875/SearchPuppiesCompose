package com.example.searchdogspracticecompose.domain

import com.example.searchdogspracticecompose.data.repository.DogsRepository
import javax.inject.Inject

class GetSelectBreed @Inject constructor(private val dogsRepository: DogsRepository) {

    operator fun invoke(): String {
        return dogsRepository.getSelectBreed()
    }
}