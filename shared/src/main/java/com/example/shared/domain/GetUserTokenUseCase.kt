package com.example.shared.domain

import com.example.core.base.BaseUseCase
import com.example.core.wrapper.ViewResource
import com.example.shared.data.repository.UserPreferenceRepository
import com.example.shared.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUserTokenUseCase(
    private val repository: UserPreferenceRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<Nothing, String>(dispatcher) {

    override suspend fun execute(param: Nothing?): Flow<ViewResource<String>> {
        return flow {
            repository.getUserToken().collect {
                it.suspendSubscribe(doOnSuccess = { result ->
                    emit(ViewResource.Success(result.payload.orEmpty()))
                }, doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                })
            }
        }
    }
}
