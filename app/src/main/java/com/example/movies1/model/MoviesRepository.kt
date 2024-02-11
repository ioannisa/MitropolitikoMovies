package com.example.movies1.model

import android.util.Log
import com.example.movies1.TAG
import com.example.movies1.network.Movie
import com.example.movies1.network.MoviesService

class MoviesRepository {

    fun getDummyMovies(): List<Movie> {
        val dummyMovies = listOf(
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

        return dummyMovies + dummyMovies + dummyMovies + dummyMovies + dummyMovies
    }

    suspend fun getMovies(): List<Movie> {
        val response = try {
            MoviesService.api.getMovies()
        } catch (e: Exception) {
            Log.e(TAG,"Exception with message ${e.message}")
            return emptyList()
        }

        if (response.isSuccessful && response.body() != null) {
            return response.body()!!.movies
        }

        return emptyList()
    }
}