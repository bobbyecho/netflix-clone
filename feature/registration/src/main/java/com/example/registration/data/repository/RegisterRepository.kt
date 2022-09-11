package com.example.registration.data.repository

import com.example.core.wrapper.DataResource
import com.example.registration.data.network.datasource.RegisterDataSource
import com.example.registration.data.network.model.request.RegisterRequest
import com.example.shared.data.model.response.AuthResponse
import com.example.shared.data.model.response.BaseResponse
import com.example.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias RegisterDataResource = DataResource<BaseResponse<AuthResponse>>

interface RegisterRepository {
    suspend fun registerUser(
        birthdate: String,
        email: String,
        gender: String,
        password: String,
        username: String
    ): Flow<RegisterDataResource>
}

class RegisterRepositoryImpl(
    private val datasource: RegisterDataSource
): Repository(), RegisterRepository {
    override suspend fun registerUser(
        birthdate: String,
        email: String,
        gender: String,
        password: String,
        username: String
    ): Flow<RegisterDataResource> {
        return flow {
            emit(safeNetworkCall {
                datasource.registerUser(
                   RegisterRequest(
                       email = email,
                       username = username,
                       password = password,
                       gender = gender,
                       birthdate = birthdate
                   )
                )
            })
        }
    }
}