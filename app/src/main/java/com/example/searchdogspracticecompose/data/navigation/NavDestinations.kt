package com.example.searchdogspracticecompose.data.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeScreenRoute

@Serializable
object MainScreenRoute

@Serializable
data class ZoomScreenRoute(val urlImage : String)