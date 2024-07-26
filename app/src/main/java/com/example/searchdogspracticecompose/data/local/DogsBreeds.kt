package com.example.searchdogspracticecompose.data.local

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogsBreeds @Inject constructor() {

    var dogBreeds : List<String> = emptyList()

}