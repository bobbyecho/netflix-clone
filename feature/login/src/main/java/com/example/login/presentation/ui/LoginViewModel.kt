package com.example.login.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.wrapper.ViewResource
import com.example.login.domain.LoginUserUseCase
import com.example.shared.data.model.viewParam.UserViewParam
import kotlinx.coroutines.launch

open class LoginViewModel(private val loginUserUseCase: LoginUserUseCase) : ViewModel() {
    val loginResult = MutableLiveData<ViewResource<UserViewParam?>>()

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            loginUserUseCase(LoginUserUseCase.Param(email, password)).collect {
                loginResult.postValue(it)
            }
        }
    }
}