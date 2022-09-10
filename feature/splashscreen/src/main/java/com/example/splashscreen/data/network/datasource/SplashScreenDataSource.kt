package com.example.splashscreen.data.network.datasource

import com.example.shared.data.model.response.BaseResponse
import com.example.splashscreen.data.network.model.response.SyncResponse
import com.example.splashscreen.data.network.service.SplashScreenFeatureAPI

interface SplashScreenDataSource {
    suspend fun doUserSync(): BaseResponse<SyncResponse>
}

class SplashScreenDataSourceImpl(private val service: SplashScreenFeatureAPI): SplashScreenDataSource {
    override suspend fun doUserSync(): BaseResponse<SyncResponse> {
        return service.doUserSync()
    }
}