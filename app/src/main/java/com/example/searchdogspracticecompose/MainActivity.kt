package com.example.searchdogspracticecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.searchdogspracticecompose.ui.theme.screens.mainDogsScreen.MainScreen
import com.example.searchdogspracticecompose.ui.theme.screens.navigation.Routes
import com.example.searchdogspracticecompose.ui.theme.screens.zoomScreen.ZoomScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Routes.MainScreen.route) {
                composable(Routes.MainScreen.route) {
                    MainScreen(navController)
                }
                composable(Routes.ZoomScreen.route){
                    ZoomScreen()
                }
            }
        }
    }
}

