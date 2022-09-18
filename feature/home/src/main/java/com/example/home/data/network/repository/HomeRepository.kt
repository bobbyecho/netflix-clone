package com.example.home.data.network.repository

import com.example.core.wrapper.DataResource
import com.example.home.data.network.datasource.HomeDataSource
import com.example.home.data.network.model.response.HomeFeedsResponse
import com.example.shared.data.model.response.BaseResponse
import com.example.shared.data.model.response.MovieResponse
import com.example.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias HomeDataResource = DataResource<BaseResponse<HomeFeedsResponse>>
typealias WatchListDataResource = DataResource<BaseResponse<List<MovieResponse>>>

interface HomeRepository {
    suspend fun fetchHomeFeeds(): Flow<HomeDataResource>
    suspend fun fetchWatchList(): Flow<WatchListDataResource>
}

class HomeRepositoryImpl(private val dataResource: HomeDataSource): Repository(), HomeRepository {
    override suspend fun fetchHomeFeeds(): Flow<HomeDataResource> = flow {
        emit(safeNetworkCall { dataResource.fetchHomeFeeds() })
    }

    override suspend fun fetchWatchList(): Flow<WatchListDataResource> = flow {
        emit(safeNetworkCall { dataResource.fetchWatchList() })
    }
}