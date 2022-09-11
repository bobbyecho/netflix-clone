package com.example.registration.data.network.services

import com.example.registration.data.network.model.request.RegisterRequest
import com.example.shared.data.model.response.AuthResponse
import com.example.shared.data.model.response.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterFeatureApi {
    @POST("/api/v1/user/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): BaseResponse<AuthResponse>
}
