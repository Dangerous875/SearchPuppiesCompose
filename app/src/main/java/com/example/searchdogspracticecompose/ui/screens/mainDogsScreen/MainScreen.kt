package com.example.searchdogspracticecompose.ui.screens.mainDogsScreen

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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.searchdogspracticecompose.data.local.OrientationScreen
import com.example.searchdogspracticecompose.data.navigation.ZoomScreenRoute
import com.example.searchdogspracticecompose.ui.component.SetOrientationScreen
import com.example.searchdogspracticecompose.ui.screens.mainDogsScreen.viewmodel.MainScreenViewModel

@Composable
fun MainScreen(navController: NavHostController, viewModel: MainScreenViewModel = hiltViewModel()) {
    val isLoading by viewModel.isLoading.collectAsState()
    val dogList by viewModel.lisDogs.collectAsState()
    val allBreeds by viewModel.allBreeds.collectAsState()
    val context = LocalContext.current

    SetOrientationScreen(context = context, orientation = OrientationScreen.PORTRAIT.orientation)

    if (isLoading) {
        ShowLoading()
    } else {
        ContentView(viewModel, dogList, navController, allBreeds)
    }


}

@Composable
fun ContentView(
    viewModel: MainScreenViewModel,
    dogList: List<String>,
    navController: NavHostController,
    allBreeds: List<String>
) {

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val topLine = createGuidelineFromTop(0.06f)
        val (idTextField, idRecyclerView) = createRefs()

        var showDialog by rememberSaveable { mutableStateOf(false) }
        var query by rememberSaveable {
            mutableStateOf("")
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(idTextField) {
                    top.linkTo(topLine)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(idRecyclerView.top)
                }
                .clickable { showDialog = true }
        ) {
            TextField(
                value = query,
                onValueChange = { },
                label = { Text(text = "Search ...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = { }
                ),
                singleLine = true,
                enabled = false, //
                modifier = Modifier.fillMaxWidth()
            )
        }
        if (showDialog) {
            ShowAlertDialog(allBreeds, { query = it }, viewModel) { showDialog = it }
        }
        ShowListDogs(idTextField, idRecyclerView, dogList, navController)


    }
}

@Composable
fun ConstraintLayoutScope.ShowListDogs(
    idTextField: ConstrainedLayoutReference,
    idRecyclerView: ConstrainedLayoutReference,
    dogList: List<String>,
    navController: NavHostController
) {
    LazyColumn(modifier = Modifier
        .constrainAs(idRecyclerView) {
            top.linkTo(idTextField.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }
        .fillMaxSize()
    ) {
        items(dogList) { imageUrl ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 4.dp)
                    .height(250.dp)
                    .clickable {
                        navController.navigate(ZoomScreenRoute(imageUrl))
                    },
                elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}


@Composable
private fun ShowAlertDialog(
    allBreeds: List<String>,
    setQuery: (String) -> Unit,
    viewModel: MainScreenViewModel,
    closeDialog: (Boolean) -> Unit
) {
    AlertDialog(
        modifier = Modifier.height(600.dp),
        onDismissRequest = { closeDialog(false) },
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
                                setQuery(breed)
                                viewModel.getDogsByBreeds(breed)
                                closeDialog(false)
                            }
                            .padding(16.dp), fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        },
        confirmButton = {
            Button(onClick = { closeDialog(false) }) {
                Text("Close")
            }
        }
    )
}

@Composable
fun ShowLoading() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}
