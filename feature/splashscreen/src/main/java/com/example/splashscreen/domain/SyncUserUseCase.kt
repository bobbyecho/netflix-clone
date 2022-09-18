package com.example.splashscreen.domain

import android.widget.Toast
import com.example.core.base.BaseUseCase
import com.example.core.wrapper.ViewResource
import com.example.shared.data.model.mapper.UserMapper
import com.example.shared.data.model.viewParam.UserViewParam
import com.example.shared.data.repository.UserPreferenceRepository
import com.example.shared.utils.ext.suspendSubscribe
import com.example.splashscreen.data.repository.SplashScreenRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

typealias SyncResult = Pair<Boolean, UserViewParam?>

class SyncUserUseCase(
    private val splashScreenRepository: SplashScreenRepository,
    private val userPreferenceRepository: UserPreferenceRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<Nothing, SyncResult>(dispatcher) {

    override suspend fun execute(param: Nothing?): Flow<ViewResource<SyncResult>> {
        return flow {
            userPreferenceRepository.isUserLoggedIn().first().suspendSubscribe(
                doOnSuccess = { result ->
                    if (result.payload == true) {
                        splashScreenRepository.doUserSync().collect {
                            it.suspendSubscribe(
                                doOnSuccess = { response ->
                                    emit(ViewResource.Success(Pair(true, UserMapper.toViewParam(response.payload?.data?.userResponse))))
                                }, doOnError = { error ->
                                    emit(ViewResource.Error(error.exception))
                                })
                        }
                    } else {
                        emit(
                            ViewResource.Success(Pair(false, null))
                        )
                    }
                },
                doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                })
        }
    }
}