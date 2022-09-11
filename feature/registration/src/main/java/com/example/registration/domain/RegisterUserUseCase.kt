package com.example.registration.domain

import com.example.core.base.BaseUseCase
import com.example.core.wrapper.ViewResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.registration.data.repository.RegisterRepository
import com.example.shared.data.model.mapper.UserMapper
import com.example.shared.data.model.viewParam.UserViewParam
import com.example.shared.domain.SaveAuthDataUseCase
import com.example.shared.utils.GenderUtils
import com.example.shared.utils.ext.suspendSubscribe
import kotlinx.coroutines.flow.first

class RegisterUserUseCase(
    private val checkRegisterFieldUseCase: CheckRegisterFieldUseCase,
    private val saveAuthDataUseCase: SaveAuthDataUseCase,
    private val registerRepository: RegisterRepository,
    dispatcher: CoroutineDispatcher
): BaseUseCase<RegisterUserUseCase.RegisterParam, UserViewParam?>(dispatcher) {
    override suspend fun execute(param: RegisterParam?): Flow<ViewResource<UserViewParam?>> {
        return flow {
            mutateParam(param)?.let { p ->
                emit(ViewResource.Loading())
                checkRegisterFieldUseCase(param).first().suspendSubscribe(
                    doOnSuccess = { _ ->
                        registerRepository.registerUser(
                            email = p.email,
                            password = p.password,
                            birthdate = p.birthdate,
                            gender = p.gender,
                            username = p.username
                        ).collect { loginResult ->
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
                                }, doOnError = {

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

    private fun mutateParam(param: RegisterParam?) = param?.apply {
        this.gender = GenderUtils.parseGender(this.gender)
    }

    data class RegisterParam(
        val birthdate: String,
        val email: String,
        var gender: String,
        val password: String,
        val username: String
    )
}