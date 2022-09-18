package com.example.home.data.network.model.response

import com.example.shared.data.model.response.MovieResponse
import com.google.gson.annotations.SerializedName

data class HomeFeedsResponse(
    @SerializedName("header")
    val header: MovieResponse?,
    @SerializedName("sections")
    val sections: List<SectionResponse>?,
)
