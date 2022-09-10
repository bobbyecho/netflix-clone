package com.example.shared.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.core.wrapper.DataResource
import com.example.shared.data.model.response.UserResponse
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface UserPreferenceDataSource {
    suspend fun clearData()
    suspend fun getUserToken(): Flow<String>
    suspend fun setUserToken(newUserToken: String)
    suspend fun isUserLoggedIn(): Flow<Boolean>
    suspend fun setUserLoginStatus(isUserLoggedIn: Boolean)
    suspend fun getCurrentUser(): Flow<UserResponse>
    suspend fun setCurrentUser(user: UserResponse)
}

class UserPreferenceDataSourceImpl(
    private val dataSore: DataStore<Preferences>,
    private val gson: Gson
): UserPreferenceDataSource {
    override suspend fun clearData() {
        dataSore.edit {
            it.clear()
        }
    }

    override suspend fun getUserToken(): Flow<String> {
        return dataSore.data.map {
            it.toPreferences()[UserPreferenceKey.userToken].orEmpty()
        }
    }

    override suspend fun setUserToken(newUserToken: String) {
        dataSore.edit {
            it[UserPreferenceKey.userToken] = newUserToken
        }
    }

    override suspend fun isUserLoggedIn(): Flow<Boolean> {
        return dataSore.data.map {
            it.toPreferences()[UserPreferenceKey.isUserLoggedIn] ?: false
        }
    }

    override suspend fun setUserLoginStatus(isUserLoggedIn: Boolean) {
        dataSore.edit {
            it[UserPreferenceKey.isUserLoggedIn] = isUserLoggedIn
        }
    }

    override suspend fun getCurrentUser(): Flow<UserResponse> {
        return dataSore.data.map {
            gson.fromJson(
                it.toPreferences()[UserPreferenceKey.userObject].orEmpty(),
                UserResponse::class.java
            )
        }
    }

    override suspend fun setCurrentUser(user: UserResponse) {
        dataSore.edit {
            it[UserPreferenceKey.userObject] = gson.toJson(user)
        }
    }
}