package com.example.home.presentation.viewparam

import com.example.shared.data.model.response.MovieResponse
import com.example.shared.data.model.viewParam.MovieViewParam
import com.google.gson.annotations.SerializedName

data class SectionViewParam(
    @SerializedName("section_id")
    val sectionId: Int,
    @SerializedName("section_name")
    val sectionName: String,
    @SerializedName("contents")
    val contents: List<MovieViewParam>
)
