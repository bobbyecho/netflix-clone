package com.example.splashscreen.data.repository

import com.example.core.wrapper.DataResource
import com.example.shared.data.model.response.BaseResponse
import com.example.shared.data.repository.Repository
import com.example.splashscreen.data.network.datasource.SplashScreenDataSource
import com.example.splashscreen.data.network.model.response.SyncResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias syncDataResponse = DataResource<BaseResponse<SyncResponse>>

interface SplashScreenRepository {
    suspend fun doUserSync(): Flow<syncDataResponse>
}

class SplashScreenRepositoryImpl(private val dataSource: SplashScreenDataSource) : SplashScreenRepository,
    Repository() {
    override suspend fun doUserSync(): Flow<syncDataResponse> {
        return flow {
            emit(safeNetworkCall { dataSource.doUserSync() })
        }
    }
}