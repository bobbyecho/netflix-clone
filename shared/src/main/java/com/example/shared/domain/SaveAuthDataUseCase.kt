package com.example.shared.domain

import com.example.core.base.BaseUseCase
import com.example.core.wrapper.DataResource
import com.example.core.wrapper.ViewResource
import com.example.shared.data.model.response.UserResponse
import com.example.shared.data.repository.UserPreferenceRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class SaveAuthDataUseCase(private val repository: UserPreferenceRepository, dispatcher: CoroutineDispatcher):
    BaseUseCase<SaveAuthDataUseCase.Param, Boolean>(dispatcher) {
    override suspend fun execute(param: Param?): Flow<ViewResource<Boolean>> = flow {
        param?.let {
            val saveLoginStatus = repository.updateUserLoginStatus(param.isLoggedIn).first()
            val setLoggedUSer = repository.setCurrentUser(param.user).first()
            val saveToken = repository.updateUserToken(param.AuthToken).first()

            if (setLoggedUSer is DataResource.Success && saveToken is DataResource.Success && saveLoginStatus is DataResource.Success) {
                emit(ViewResource.Success(true))
            } else {
                emit(ViewResource.Error(IllegalStateException("Failed to save local data")))
            }
        }
    }

    data class Param(val isLoggedIn: Boolean, val AuthToken: String, val user: UserResponse)
}