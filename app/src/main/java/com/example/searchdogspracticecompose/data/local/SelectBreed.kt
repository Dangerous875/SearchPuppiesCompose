package com.example.searchdogspracticecompose.data.local

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SelectBreed @Inject constructor() {
    var selectBreed : String? = null ?: "akita"
}