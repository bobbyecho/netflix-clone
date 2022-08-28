package com.example.shared.data.remote.services

import com.example.core.wrapper.DataResource
import retrofit2.http.HTTP

import com.example.shared.data.model.request.WatchlistRequest;
import com.example.shared.repository.Repository
import com.example.shared.repository.SharedApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import retrofit2.http.Body;
import retrofit2.http.POST;

interface SharedFeatureApi {
    @POST("/api/v1/watchlist")
    suspend fun addWatchlist(@Body request: WatchlistRequest): Flow<DataResource<Any>>

    @HTTP(method = "DELETE", path = "/api/v1/watchlist", hasBody = true)
    suspend fun removeWatchlist(@Body request: WatchlistRequest): Flow<DataResource<Any>>
}

class SharedApiRepositoryImpl(private val service: SharedFeatureApi) : Repository(),
    SharedApiRepository {

    override suspend fun addWatchlist(movieId: String): Flow<DataResource<Any>> {
        return flow {
            emit(safeNetworkCall { service.addWatchlist(WatchlistRequest(movieId)) })
        }
    }

    override suspend fun removeWatchlist(movieId: String): Flow<DataResource<Any>> {
        return flow {
            emit(safeNetworkCall { service.removeWatchlist(WatchlistRequest(movieId)) })
        }
    }

}