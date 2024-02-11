package com.example.movies1.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.movies1.viewmodel.MoviesViewModel
import com.example.movies1.R
import com.example.movies1.network.Movie
import com.example.movies1.network.NetworkUtils
import com.example.movies1.ui.theme.Movies1Theme

@Composable
fun MoviesListScreen(viewModel: MoviesViewModel) {
    //var movies by remember { mutableStateOf<List<Movie>>(emptyList()) }
    val movies by viewModel.movies.collectAsState()

    LazyColumn(
        modifier = Modifier.padding(top = 4.dp)
    ) {
        items(
            items = movies,
            //key = { it.id }
        ) { movie ->


            RowItem(
                movie = movie,
                modifier = Modifier
                    .clickable {

                    }
            )
        }
    }
}

@Composable
fun RowItem(movie: Movie, modifier: Modifier = Modifier) {
    //Text(movie.title)

    Card(
        modifier = modifier.
        padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Row {
            ThumbnailLoader(imagePath = movie.poster_path)
            Column(
                modifier = Modifier
                    .align(Alignment.Top)
                    .fillMaxSize()
                    .heightIn(120.dp)
                    .padding(8.dp)
                ,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleLarge,

                    )

                Text(text = movie.release_date.substring(0, 4))
            }
        }
    }
}

@Composable
fun ThumbnailLoader(imagePath: String?) {
    val imagePainter: Painter

    if (!imagePath.isNullOrEmpty()) {
        val imageUrl = NetworkUtils.getImageUrl(imagePath, NetworkUtils.ImageSize.SMALL)
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
        modifier = Modifier.size(120.dp)
    )
}

@Preview(showBackground = true, device = Devices.PIXEL_2_XL)
@Composable
fun GreetingPreview() {
    Movies1Theme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            MoviesListScreen(viewModel = MoviesViewModel())
        }
    }
}