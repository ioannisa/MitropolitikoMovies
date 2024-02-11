package com.example.movies1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movies1.network.Movie
import com.example.movies1.ui.theme.Movies1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Movies1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MoviesListScreen()
                }
            }
        }
    }
}

@Composable
fun MoviesListScreen() {
    var movies by remember { mutableStateOf<List<Movie>>(emptyList()) }

    LaunchedEffect(Unit) {
        var dummyMovies = listOf(
            Movie(
                adult = false,
                backdrop_path = "/kXfqcdQKsToO0OUXHcrrNCHDBzO.jpg",
                genre_ids = listOf(),
                id = 278,
                original_language = "en",
                original_title = "The Shawshank Redemption",
                overview = "Ένας νεαρός και επιτυχημένος τραπεζίτης καταλήγει άδικα στη φυλακή για τη δολοφονία της συζύγου του και του εραστή της. Η φιλία του, όμως, με έναν ισοβίτη συγκρατούμενό του θα αλλάξει τα πάντα...\",\n",
                popularity = 141.75,
                poster_path = "/kGzFbGhp99zva6oZODW5atUtnqi.jpg",
                release_date = "1994-09-23",
                title = "Τελευταία Έξοδος: Ρίτα Χέιγουορθ",
                video = false,
                vote_average = 8.707,
                vote_count = 25124
            ),
            Movie(
                adult = false,
                backdrop_path = "/kGzFbGhp99zva6oZODW5atUtnqi.jpg",
                genre_ids = listOf(),
                id = 240,
                original_language = "en",
                original_title = "The Godfather Part II",
                overview = "Ο Μάικλ Κoρλεόvε, διάδoχoς της εγκληματικής αυτoκρατoρίας τoυ πατέρα τoυ, δεv είvαι πια o ιδεαλιστής vέoς πoυ πoλέμησε με αυτoθυσία στov Β Παγκόσμιo Πόλεμo. Αφoύ εξασφαλίζει τη συvεργασία εvός δυvαμικoύ γερoυσιαστή, εκβιάζovτάς τov μ έvα σκάvδαλo από τo παρελθόv τoυ, o Μάικλ πηγαίvει στηv Κoύβα για vα αvoίξει έvα πoλυτελές καζίvo σε συvεργασία με τov Εβραίo Μαφιόζo Χάιμαv Ρoθ. Ωστόσo, εvώ εγκαθιδρύει τηv απόλυτη εξoυσία τoυ στον υπόκοσμο, o Μάικλ Κoρλεόvε απoκτά όλo και περισσότερoυς εχθρoύς, εvώ η σχέση τoυ με τηv έγκυo γυvαίκα τoυ, Κέι, περvάει μια σoβαρή κρίση.",
                popularity = 88.092,
                poster_path = "/hek3koDUyRQk7FIhPXsa6mT2Zc3.jpg",
                release_date = "1974-12-20",
                title = "Ο Νονός 2",
                video = false,
                vote_average = 8.589,
                vote_count = 11541
            ),
        )

        movies = dummyMovies + dummyMovies + dummyMovies + dummyMovies + dummyMovies
    }

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

//    if (!imagePath.isNullOrEmpty()) {
//        val imageUrl = NetworkUtils.getImageUrl(imagePath, NetworkUtils.ImageSize.SMALL)
//        imagePainter = rememberAsyncImagePainter(imageUrl)
//    }
//    else {
//        imagePainter = painterResource(R.drawable.ic_launcher_foreground)
//    }

    imagePainter = painterResource(R.drawable.android)

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
            MoviesListScreen()
        }
    }
}