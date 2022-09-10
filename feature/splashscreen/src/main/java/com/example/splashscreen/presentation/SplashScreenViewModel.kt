package com.example.splashscreen.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.wrapper.ViewResource
import com.example.splashscreen.domain.SyncResult
import com.example.splashscreen.domain.SyncUserUseCase
import kotlinx.coroutines.launch

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class SplashScreenViewModel(private val syncUserUseCase: SyncUserUseCase) : ViewModel() {

    val syncResult : MutableLiveData<ViewResource<SyncResult>> = MutableLiveData()

    fun syncUser(){
        viewModelScope.launch {
            syncUserUseCase().collect{
                syncResult.value = it
            }
        }
    }

}