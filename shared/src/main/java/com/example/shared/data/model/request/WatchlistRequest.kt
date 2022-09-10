package com.example.shared.data.model.request

import com.google.gson.annotations.SerializedName

class WatchlistRequest(
    @SerializedName("movie_id")
    val movieId: String
)