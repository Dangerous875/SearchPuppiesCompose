package com.example.searchdogspracticecompose.ui.screens.homeScreen

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.searchdogspracticecompose.R
import com.example.searchdogspracticecompose.data.local.OrientationScreen
import com.example.searchdogspracticecompose.data.navigation.FavoriteScreenRoute
import com.example.searchdogspracticecompose.data.navigation.MainScreenRoute
import com.example.searchdogspracticecompose.ui.component.SetOrientationScreen
import com.example.searchdogspracticecompose.ui.screens.homeScreen.viewmodel.HomeScreenViewModel

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeScreenViewModel = hiltViewModel()) {

    val activity = LocalContext.current as Activity
    val isLoading by viewModel.isLoading.collectAsState()
    val allBreeds by viewModel.allBreeds.collectAsState()
    val context = LocalContext.current

    SetOrientationScreen(context = context, orientation = OrientationScreen.PORTRAIT.orientation)

    if (isLoading) {
        ShowLoading()
    } else {
        ShowContent(allBreeds, viewModel, navController)
    }

    BackHandler {
        activity.finish()
    }
}

@Composable
fun ShowContent(
    allBreeds: List<String>,
    viewModel: HomeScreenViewModel,
    navController: NavHostController
) {
    TopBarHome(navController)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 46.dp)
            .background(Color(0xFF42A8F8)), contentAlignment = Alignment.Center
    ) {
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

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF42A8F8)), contentPadding = PaddingValues(4.dp)
            ) {
                items(allBreeds) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp)
                            .clickable {
                                viewModel.setSelectBreed(it)
                                navController.navigate(MainScreenRoute)
                            },
                        shape = RoundedCornerShape(8.dp),
                        elevation = CardDefaults.cardElevation(16.dp),
                        colors = CardColors(
                            contentColor = Color.Black,
                            containerColor = Color(0xFF81C6FC),
                            disabledContentColor = Color.White,
                            disabledContainerColor = Color.White
                        ), border = BorderStroke(2.dp, Color.Black)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_dogtwo),
                                    contentDescription = "decoration"
                                )
                                Text(
                                    text = it,
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.Serif
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_dogtwo),
                                    contentDescription = "decoration"
                                )
                            }


                        }
                    }
                }
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarHome(navController: NavHostController) {
    TopAppBar(
        modifier = Modifier.height(48.dp),
        title = {
            Text(
                text = " SearchDogsApp ",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                textAlign = TextAlign.Start,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(Color.Black),
        actions = {
            Text(text = "Favorites", color = Color.White, fontSize = 16.sp)
            IconButton(onClick = { navController.navigate(FavoriteScreenRoute) }) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    )
}

@Composable
fun ShowLoading() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = Color.Black)
    }
}
