package com.example.searchdogspracticecompose.domain

import com.example.searchdogspracticecompose.data.repository.DogsRepository
import javax.inject.Inject

class DeleteDBUseCases @Inject constructor(private val repository: DogsRepository) {

    suspend operator fun invoke(){
        repository.deleteAllItemsFromDB()
    }
}