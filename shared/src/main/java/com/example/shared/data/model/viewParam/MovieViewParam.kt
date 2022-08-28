package com.example.shared.data.model.viewParam

import com.google.gson.annotations.SerializedName

class MovieViewParam(
    val cast: List<String>,
    val category: List<String>,
    val director: String,
    val filmRate: String,
    val id: Int,
    val overview: String,
    val posterUrl: String,
    val releaseDate: String,
    val runtime: Int,
    val title: String,
    val trailerUrl: String,
    val videoUrl: String,
    val isUserWatchlist: Boolean,
)