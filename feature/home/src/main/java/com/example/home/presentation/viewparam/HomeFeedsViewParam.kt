package com.example.home.presentation.viewparam

import com.example.shared.data.model.response.MovieResponse
import com.example.shared.data.model.viewParam.MovieViewParam
import com.google.gson.annotations.SerializedName

data class HomeFeedsViewParam(
    @SerializedName("header")
    val header: MovieViewParam,
    @SerializedName("sections")
    val sections: List<SectionViewParam>,
)
