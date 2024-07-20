package com.example.searchdogspracticecompose.data.model

import com.google.gson.annotations.SerializedName

data class DogResponse(
    @SerializedName("status") var status: Boolean,
    @SerializedName("message") var message: List<String>
)
