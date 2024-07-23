package com.example.searchdogspracticecompose.data.network

import com.example.searchdogspracticecompose.data.model.DogResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

// "{dogBreed}/images" APIService

interface APIService {

    @GET("{dogBreed}/images")
    suspend fun getDogsByApi(@Path("dogBreed") query: String): Response<DogResponse>
}