package com.example.searchdogspracticecompose.ui.screens.mainDogsScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.searchdogspracticecompose.data.local.OrientationScreen
import com.example.searchdogspracticecompose.data.navigation.ZoomScreenRoute
import com.example.searchdogspracticecompose.domain.model.Dog
import com.example.searchdogspracticecompose.ui.component.SetOrientationScreen
import com.example.searchdogspracticecompose.ui.component.TopActionBar
import com.example.searchdogspracticecompose.ui.screens.mainDogsScreen.viewmodel.MainScreenViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController, viewModel: MainScreenViewModel = hiltViewModel()) {
    val context = LocalContext.current
    SetOrientationScreen(context = context, orientation = OrientationScreen.PORTRAIT.orientation)
    var showDialog by rememberSaveable { mutableStateOf(false) }
    val allBreeds by viewModel.allBreeds.collectAsState()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
    ) {
        val (idTopBar, idContentView) = createRefs()
        Box(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(idTopBar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(idContentView.top)
            }) { TopActionBar { showDialog = it } }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(idContentView) {
                    top.linkTo(idTopBar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }) {
            ContentView(navController, viewModel)
        }
    }

    if (showDialog) {
        ShowAlertDialog(viewModel, allBreeds) { showDialog = it }
    }
}


@Composable
fun ContentView(
    navController: NavHostController,
    viewModel: MainScreenViewModel
) {
    val isLoading by viewModel.isLoading.collectAsState()
    val dogList by viewModel.lisDogs.collectAsState()

    if (isLoading) {
        ShowLoading()
    } else {
        ShowListDogs(navController, dogList)
    }
}

@Composable
fun ShowListDogs(navController: NavHostController, dogList: List<Dog>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(dogList) { dog ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 4.dp)
                    .height(250.dp)
                    .clickable {
                        navController.navigate(ZoomScreenRoute(dog.imageUrl))
                    },
                elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = rememberAsyncImagePainter(dog.imageUrl),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Icon(
                        imageVector = getIcon(dog),
                        contentDescription = "Favorite",
                        modifier = Modifier
                            .align(
                                Alignment.BottomStart
                            )
                            .padding(16.dp).clickable {  }, tint = Color.Red
                    )
                }

            }
        }
    }
}

fun getIcon(dog: Dog): ImageVector {
    return if (dog.iconFavorite){
        Icons.Default.Favorite
    }else{
        Icons.Default.FavoriteBorder
    }
}

@Composable
fun ShowAlertDialog(
    viewModel: MainScreenViewModel,
    allBreeds: List<String>,
    onChangeDialog: (Boolean) -> Unit
) {
    AlertDialog(
        modifier = Modifier.height(600.dp),
        onDismissRequest = { onChangeDialog(false) },
        title = { Text(text = "Select an option") },
        text = {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                allBreeds.forEach { breed ->
                    Text(
                        text = breed,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.getDogsByBreeds(breed)
                                onChangeDialog(false)
                            }
                            .padding(16.dp), fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        },
        confirmButton = {
            Button(onClick = { onChangeDialog(false) }) {
                Text("Close")
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











