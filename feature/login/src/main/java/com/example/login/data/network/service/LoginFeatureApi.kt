package com.example.login.data.network.service

import com.example.login.data.network.model.request.LoginRequest
import com.example.shared.data.model.response.AuthResponse
import com.example.shared.data.model.response.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginFeatureApi {
    @POST("/api/v1/user/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): BaseResponse<AuthResponse>
}