package com.example.login.domain

import com.example.core.base.BaseUseCase
import com.example.core.wrapper.ViewResource
import com.example.login.data.network.repository.LoginRepository
import com.example.shared.data.model.mapper.UserMapper
import com.example.shared.data.model.viewParam.UserViewParam
import com.example.shared.domain.SaveAuthDataUseCase
import com.example.shared.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class LoginUserUseCase(
    private val checkLoginFieldUseCase: CheckLoginFieldUseCase,
    private val saveAuthDataUseCase: SaveAuthDataUseCase,
    private val loginRepository: LoginRepository,
    dispatcher: CoroutineDispatcher
): BaseUseCase<LoginUserUseCase.Param, UserViewParam?>(dispatcher) {
    override suspend fun execute(param: Param?): Flow<ViewResource<UserViewParam?>> {
        return flow {
            param?.let {
                emit(ViewResource.Loading())
                checkLoginFieldUseCase(param).first().suspendSubscribe(
                    doOnSuccess = { _ ->
                        loginRepository.loginUser(param.email, param.password).collect { loginResult ->
                            loginResult.suspendSubscribe(
                                doOnSuccess = {
                                    val result = loginResult.payload?.data
                                    val token = result?.token
                                    val user = result?.user

                                    if(!token.isNullOrEmpty() && user !== null) {
                                        saveAuthDataUseCase(SaveAuthDataUseCase.Param(true, token, user)).collect {
                                            it.suspendSubscribe (
                                                doOnSuccess = {
                                                    emit(ViewResource.Success(UserMapper.toViewParam(user)))
                                                },
                                                doOnError = { error ->
                                                    emit(ViewResource.Error(error.exception))
                                                }
                                            )
                                        }
                                    }
                                }, doOnError = { error ->
                                    emit(ViewResource.Error(error.exception))
                                }
                            )
                        }
                    },
                    doOnError = { error ->
                        emit(ViewResource.Error(error.exception))
                    }
                )
            }?: throw IllegalStateException("Param Required")
        }
    }

    data class Param(val email: String, val password: String)
}