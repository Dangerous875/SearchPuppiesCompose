package com.example.searchdogspracticecompose.core

import com.example.searchdogspracticecompose.domain.model.Dog

fun String.toDomain() =  Dog(this, false)
