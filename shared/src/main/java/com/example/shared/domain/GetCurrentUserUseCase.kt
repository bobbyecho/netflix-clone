package com.example.shared.domain

import com.example.core.base.BaseUseCase
import com.example.core.wrapper.DataResource
import com.example.core.wrapper.ViewResource
import com.example.shared.data.model.mapper.UserMapper
import com.example.shared.data.model.viewParam.UserViewParam
import com.example.shared.data.repository.UserPreferenceRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GetCurrentUserUseCase(
    private val repository: UserPreferenceRepository,
    dispatcher: CoroutineDispatcher
): BaseUseCase<Nothing, UserViewParam>(dispatcher) {
    override suspend fun execute(param: Nothing?): Flow<ViewResource<UserViewParam>> = flow {
        repository.getCurrentUser().map {
            when(it) {
                is DataResource.Success -> {
                    ViewResource.Success(UserMapper.toViewParam(it.payload))
                }
                is DataResource.Error -> {
                    ViewResource.Error(it.exception)
                }
            }
        }
    }
}