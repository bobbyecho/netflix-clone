package com.example.home.data.network.datasource

import com.example.home.data.network.model.response.HomeFeedsResponse
import com.example.home.data.network.services.HomeFeatureService
import com.example.shared.data.model.response.BaseResponse
import com.example.shared.data.model.response.MovieResponse

interface HomeDataSource {
    suspend fun fetchHomeFeeds(): BaseResponse<HomeFeedsResponse>
    suspend fun fetchWatchList(): BaseResponse<List<MovieResponse>>
}

class HomeDataSourceImpl(private val api: HomeFeatureService): HomeDataSource {
    override suspend fun fetchHomeFeeds(): BaseResponse<HomeFeedsResponse> {
        return api.fetchHomeFeeds()
    }

    override suspend fun fetchWatchList(): BaseResponse<List<MovieResponse>> {
        return api.fetchWatchList()
    }
}