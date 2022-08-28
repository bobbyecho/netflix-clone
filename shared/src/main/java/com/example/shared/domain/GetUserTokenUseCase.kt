package com.example.shared.domain

import com.example.core.base.BaseUseCase
import com.example.core.wrapper.ViewResource
import com.example.shared.repository.UserPreferenceRepsitory
import com.example.shared.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GetUserTokenUseCase(
    private val repository: UserPreferenceRepsitory,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<Nothing, String>(dispatcher) {

    override suspend fun execute(param: Nothing?): Flow<ViewResource<String>> {
        return flow {
            repository.getUserToken().map {
                it.suspendSubscribe(doOnSuccess = { result ->
                    emit(ViewResource.Success(result.payload.orEmpty()))
                }, doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                })
            }
        }
    }
}
