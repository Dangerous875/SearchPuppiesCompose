package com.example.searchdogspracticecompose.ui.screens.zoomScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import com.example.searchdogspracticecompose.data.local.OrientationScreen
import com.example.searchdogspracticecompose.ui.component.SetOrientationScreen

@Composable
fun ZoomScreen(urlImage: String) {
    val context = LocalContext.current
    SetOrientationScreen(
        context = context,
        orientation = OrientationScreen.LANDSCAPE.orientation,
        hideStatusBar = true
    )
//    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//        Image(
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.Crop,
//            painter = rememberAsyncImagePainter(urlImage),
//            contentDescription = "dogPicture"
//        )
//    }

    var scale by remember { mutableFloatStateOf(1f) }
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale *= zoom
                    offsetX += pan.x
                    offsetY += pan.y
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = rememberAsyncImagePainter(urlImage),
            contentDescription = "dogPicture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offsetX,
                    translationY = offsetY
                )
        )
    }
}