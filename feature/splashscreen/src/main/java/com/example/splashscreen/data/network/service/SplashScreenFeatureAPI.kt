package com.example.splashscreen.data.network.service

import com.example.shared.data.model.response.BaseResponse
import com.example.splashscreen.data.network.model.response.SyncResponse
import retrofit2.http.GET

interface SplashScreenFeatureAPI {
    @GET("/api/v1/sync")
    suspend fun doUserSync(): BaseResponse<SyncResponse>
}