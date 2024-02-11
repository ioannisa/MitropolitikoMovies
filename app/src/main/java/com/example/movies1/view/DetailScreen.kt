package com.example.movies1.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.movies1.MyTopAppBar
import com.example.movies1.R
import com.example.movies1.network.NetworkUtils
import com.example.movies1.viewmodel.MoviesViewModel

@Composable
fun DetailScreen(viewModel: MoviesViewModel, movieId: Int, navController: NavHostController) {

    val movie by viewModel.selectedMovie.collectAsState()

    Scaffold(
        topBar = {
            MyTopAppBar(title = movie?.title, onBackPress = { navController.navigateUp() })
        }
    ) { paddingValues ->

        // obtain the movie from view model when movieId changes
        LaunchedEffect(movieId) {
            viewModel.selectMovie(movieId)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    start = paddingValues.calculateStartPadding(layoutDirection = LocalLayoutDirection.current),
                    end = paddingValues.calculateEndPadding(layoutDirection = LocalLayoutDirection.current),
                    bottom = paddingValues.calculateBottomPadding()
                )
                .verticalScroll(rememberScrollState())
        ) {
            // simply show the obtained movieId from the arguments
            //Text("Hello Movie Detail $movieId")
            movie?.let { selectedMovie ->
                MainImageLoader(imagePath = selectedMovie.backdrop_path)
                Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("${selectedMovie.title}", fontSize = 24.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(" ${selectedMovie.overview}")
                }
            }
        }
    }
}

@Composable
fun MainImageLoader(imagePath: String?) {
    val imagePainter: Painter

    if (!imagePath.isNullOrEmpty()) {
        val imageUrl = NetworkUtils.getImageUrl(imagePath, NetworkUtils.ImageSize.LARGE)
        imagePainter = rememberAsyncImagePainter(imageUrl)
    }
    else {
        imagePainter = painterResource(R.drawable.ic_launcher_foreground)
    }

    Image(
        painter = imagePainter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        alignment = Alignment.TopCenter,
        modifier = Modifier.size(400.dp)
    )
}