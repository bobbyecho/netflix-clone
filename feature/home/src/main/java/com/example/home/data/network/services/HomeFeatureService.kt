package com.example.home.data.network.services

import com.example.home.data.network.model.response.HomeFeedsResponse
import com.example.shared.data.model.response.BaseResponse
import com.example.shared.data.model.response.MovieResponse
import retrofit2.http.GET

interface HomeFeatureService {
    @GET("api/v1/homepage")
    suspend fun fetchHomeFeeds(): BaseResponse<HomeFeedsResponse>

    @GET("api/v1/watchlist")
    suspend fun fetchWatchList(): BaseResponse<List<MovieResponse>>
}