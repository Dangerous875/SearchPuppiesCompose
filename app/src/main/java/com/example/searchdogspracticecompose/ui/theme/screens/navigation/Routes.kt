package com.example.searchdogspracticecompose.ui.theme.screens.navigation

sealed class Routes(val route : String) {
    data object MainScreen : Routes("MainScreen")
    data object ZoomScreen : Routes("ZoomScreen")
}