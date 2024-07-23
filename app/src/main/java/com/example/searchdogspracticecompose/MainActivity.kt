package com.example.searchdogspracticecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.searchdogspracticecompose.data.navigation.HomeScreen
import com.example.searchdogspracticecompose.data.navigation.MainScreen
import com.example.searchdogspracticecompose.data.navigation.ZoomScreenn
import com.example.searchdogspracticecompose.ui.screens.homeScreen.HomeScreen
import com.example.searchdogspracticecompose.ui.screens.mainDogsScreen.MainScreen
import com.example.searchdogspracticecompose.ui.screens.zoomScreen.ZoomScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = HomeScreen) {
                composable<MainScreen> { MainScreen(navController) }

                composable<HomeScreen> { HomeScreen(navController) }

                composable<ZoomScreenn> {
                    val args = it.toRoute<ZoomScreenn>()
                    ZoomScreen(args.urlImage)
                }

            }
            // instanciar dagger y permisos internet
            // navController NavHost configurar navegacion

        }
    }
}

