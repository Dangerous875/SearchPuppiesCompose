package com.example.searchdogspracticecompose.ui.screens.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.searchdogspracticecompose.data.local.OrientationScreen
import com.example.searchdogspracticecompose.data.navigation.MainScreen
import com.example.searchdogspracticecompose.ui.component.SetOrientationScreen
import com.example.searchdogspracticecompose.ui.screens.homeScreen.viewmodel.HomeScreenViewModel

@Composable
fun HomeScreen(navController: NavHostController , viewModel: HomeScreenViewModel = hiltViewModel()) {

    val isLoading by viewModel.isLoading.collectAsState()
    val allBreeds by viewModel.allBreeds.collectAsState()
    val context = LocalContext.current

    SetOrientationScreen(context = context, orientation = OrientationScreen.PORTRAIT.orientation)
    if (isLoading) {
        ShowLoading()
    } else {
        ShowContent(allBreeds, viewModel, navController)
    }
}

@Composable
fun ShowContent(
    allBreeds: List<String>,
    viewModel: HomeScreenViewModel,
    navController: NavHostController
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Razas disponibles ", fontSize = 24.sp, color = Color.White)
            }

            LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(8.dp)) {
                items(allBreeds) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp)
                            .clickable {
                                viewModel.setSelectBreed(it)
                                navController.navigate(MainScreen)
                            },
                        shape = RoundedCornerShape(8.dp),
                        elevation = CardDefaults.cardElevation(16.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = it,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )

                        }
                    }
                }
            }

        }
    }
}

@Composable
fun ShowLoading() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = Color.Black)
    }
}
