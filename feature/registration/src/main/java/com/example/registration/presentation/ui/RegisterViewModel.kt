package com.example.registration.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.wrapper.ViewResource
import com.example.registration.domain.RegisterUserUseCase
import com.example.shared.data.model.viewParam.UserViewParam
import kotlinx.coroutines.launch

open class RegisterViewModel(private val registerUserUseCase: RegisterUserUseCase) : ViewModel() {
    val registerResult = MutableLiveData<ViewResource<UserViewParam?>>()

    fun registerUser(
        birthdate: String,
        email: String,
        gender: String,
        password: String,
        username: String
    ) {
        viewModelScope.launch {
            registerUserUseCase(RegisterUserUseCase.RegisterParam(
                birthdate = birthdate,
                email = email,
                gender = gender,
                password = password,
                username = username
            )).collect {
                registerResult.postValue(it)
            }
        }
    }
}