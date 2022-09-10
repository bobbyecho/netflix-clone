package com.example.shared.data.repository

import com.example.core.wrapper.DataResource
import com.example.shared.data.local.datastore.UserPreferenceDataSource
import com.example.shared.data.model.response.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.first

interface UserPreferenceRepository {
    suspend fun getUserToken(): Flow<DataResource<String>>
    suspend fun updateUserToken(newUserToken: String): Flow<DataResource<Unit>>
    suspend fun isUserLoggedIn(): Flow<DataResource<Boolean>>
    suspend fun updateUserLoginStatus(isUserLoggedIn: Boolean): Flow<DataResource<Unit>>
    suspend fun getCurrentUser(): Flow<DataResource<UserResponse>>
    suspend fun setCurrentUser(user: UserResponse): Flow<DataResource<Unit>>
    suspend fun clearData(): Flow<DataResource<Unit>>
}

class UserPreferenceRepositoryImpl(private val dataSource: UserPreferenceDataSource):
    Repository(), UserPreferenceRepository {

    override suspend fun getUserToken(): Flow<DataResource<String>> = flow {
        emit(proceed { dataSource.getUserToken().first() })
    }

    override suspend fun updateUserToken(newUserToken: String): Flow<DataResource<Unit>> = flow {
        emit(proceed { dataSource.setUserToken(newUserToken) })
    }

    override suspend fun isUserLoggedIn(): Flow<DataResource<Boolean>> = flow {
        emit(proceed { dataSource.isUserLoggedIn().first() })
    }

    override suspend fun updateUserLoginStatus(isUserLoggedIn: Boolean): Flow<DataResource<Unit>> =
        flow {
            emit(proceed { dataSource.setUserLoginStatus(isUserLoggedIn) })
        }

    override suspend fun getCurrentUser(): Flow<DataResource<UserResponse>> = flow {
        emit(proceed { dataSource.getCurrentUser().first() })
    }

    override suspend fun setCurrentUser(user: UserResponse) = flow {
        emit(proceed { dataSource.setCurrentUser(user) })
    }

    override suspend fun clearData() = flow{
        emit(proceed { dataSource.clearData() })
    }
}