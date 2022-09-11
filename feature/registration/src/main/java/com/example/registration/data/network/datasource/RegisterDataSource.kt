package com.example.registration.data.network.datasource


import com.example.registration.data.network.model.request.RegisterRequest
import com.example.registration.data.network.services.RegisterFeatureApi
import com.example.shared.data.model.response.AuthResponse
import com.example.shared.data.model.response.BaseResponse

interface RegisterDataSource {
    suspend fun registerUser(registerRequest: RegisterRequest): BaseResponse<AuthResponse>
}

class RegisterDataSourceImpl(private val api: RegisterFeatureApi): RegisterDataSource {
    override suspend fun registerUser(registerRequest: RegisterRequest): BaseResponse<AuthResponse> {
        return api.registerUser(registerRequest)
    }
}
