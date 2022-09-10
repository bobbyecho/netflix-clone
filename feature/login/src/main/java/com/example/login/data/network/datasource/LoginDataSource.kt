package com.example.login.data.network.datasource

import com.example.login.data.network.model.request.LoginRequest
import com.example.login.data.network.service.LoginFeatureApi
import com.example.shared.data.model.response.AuthResponse
import com.example.shared.data.model.response.BaseResponse

interface LoginDataSource {
    suspend fun loginUser(loginRequest: LoginRequest): BaseResponse<AuthResponse>
}

class LoginDataSourceImpl(private val api: LoginFeatureApi): LoginDataSource {
    override suspend fun loginUser(loginRequest: LoginRequest): BaseResponse<AuthResponse> {
        return api.loginUser(loginRequest)
    }
}
