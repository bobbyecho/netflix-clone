package com.example.login.domain

import com.example.login.R
import com.example.core.base.BaseUseCase
import com.example.core.exception.FieldErrorException
import com.example.core.wrapper.ViewResource
import com.example.login.constants.LoginFieldConstants.FIELD_EMAIL
import com.example.login.constants.LoginFieldConstants.FIELD_PASSWORD
import com.example.shared.utils.StringUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias CheckFieldLoginResult = List<Pair<Int, Int>>

class CheckLoginFieldUseCase(
    dispatcher: CoroutineDispatcher
): BaseUseCase<LoginUserUseCase.Param, CheckFieldLoginResult>(dispatcher) {
    override suspend fun execute(param: LoginUserUseCase.Param?): Flow<ViewResource<CheckFieldLoginResult>> =
        flow {
            param?.let {
                val result = mutableListOf<Pair<Int, Int>>()

                checkIsEmailValid(param.email)?.let {
                    result.add(it)
                }

                checkIsPasswordValid(param.email)?.let {
                    result.add(it)
                }

                if (result.isEmpty()) {
                    emit(ViewResource.Success(result))
                } else {
                    emit(ViewResource.Error(FieldErrorException(result)))
                }

            } ?:IllegalStateException("Param Required")
        }

    private fun checkIsPasswordValid(password: String): Pair<Int, Int>? {
        return if (password.isEmpty()) {
            Pair(FIELD_PASSWORD, R.string.error_field_password)
        } else {
            null
        }
    }

    private fun checkIsEmailValid(email: String): Pair<Int, Int>? {
        return if (email.isEmpty()) {
            Pair(FIELD_EMAIL, R.string.error_field_email)
        } else if (!StringUtils.isEmailValid(email)) {
            Pair(FIELD_EMAIL, R.string.error_field_email_not_valid)
        } else {
            null
        }
    }

}