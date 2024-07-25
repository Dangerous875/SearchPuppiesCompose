package com.example.searchdogspracticecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.searchdogspracticecompose.data.navigation.FavoriteScreenRoute
import com.example.searchdogspracticecompose.data.navigation.HomeScreenRoute
import com.example.searchdogspracticecompose.data.navigation.MainScreenRoute
import com.example.searchdogspracticecompose.data.navigation.ZoomScreenRoute
import com.example.searchdogspracticecompose.ui.screens.favoriteScreen.FavoritesScreen
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
            NavHost(navController = navController, startDestination = HomeScreenRoute) {
                composable<MainScreenRoute> { MainScreen(navController) }
                composable<HomeScreenRoute> { HomeScreen(navController) }
                composable<ZoomScreenRoute> {
                    val safeArgs = it.toRoute<ZoomScreenRoute>()
                    ZoomScreen(safeArgs.urlImage)
                }
                composable<FavoriteScreenRoute> { FavoritesScreen(navController) }
            }
        }
    }
}

