package com.example.home.data.network.model.response

import com.example.shared.data.model.response.MovieResponse
import com.google.gson.annotations.SerializedName

data class SectionResponse(
    @SerializedName("section_id")
    val sectionId: Int?,
    @SerializedName("section_name")
    val sectionName: String?,
    @SerializedName("contents")
    val contents: List<MovieResponse>?
)
