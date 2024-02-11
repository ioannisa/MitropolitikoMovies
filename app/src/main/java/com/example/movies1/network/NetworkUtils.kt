package com.example.movies1.network

class NetworkUtils {
    enum class ImageSize(val pathSegment: String) {
        LARGE("w500"),
        SMALL("w200")
    }

    companion object {
        fun getImageUrl(path: String, size: ImageSize): String =
            "https://image.tmdb.org/t/p/${size.pathSegment}$path"
    }
}