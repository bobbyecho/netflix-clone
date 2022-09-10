package com.example.login.data.network.repository

import com.example.core.wrapper.DataResource
import com.example.login.data.network.datasource.LoginDataSource
import com.example.login.data.network.model.request.LoginRequest
import com.example.shared.data.model.response.AuthResponse
import com.example.shared.data.model.response.BaseResponse
import com.example.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias LoginDataResource = DataResource<BaseResponse<AuthResponse>>

interface LoginRepository {
    suspend fun loginUser(
        email: String,
        password: String,
    ): Flow<LoginDataResource>
}

class LoginRepositoryImpl(private val datasource: LoginDataSource): LoginRepository, Repository() {
    override suspend fun loginUser(email: String, password: String): Flow<LoginDataResource> {
        return flow {
            emit(
                safeNetworkCall {
                    datasource.loginUser(LoginRequest(email, password))
                }
            )
        }
    }
}