package com.example.home.presentation.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.wrapper.ViewResource
import com.example.home.domain.GetHomeFeedsUseCase
import com.example.home.domain.GetWatchListUseCase
import com.example.home.presentation.viewparam.homeitem.HomeUiItem
import com.example.shared.data.model.viewParam.MovieViewParam
import com.example.shared.data.model.viewParam.UserViewParam
import com.example.shared.delegates.AddOrRemoveWatchListDelegates
import com.example.shared.delegates.AddOrRemoveWatchlistDelegatesImpl
import com.example.shared.domain.GetCurrentUserUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getHomeFeedsUseCase: GetHomeFeedsUseCase,
    private val getUserWatchListUseCase: GetWatchListUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
): ViewModel(), AddOrRemoveWatchListDelegates by AddOrRemoveWatchlistDelegatesImpl() {
    val homeFeedsResult = MutableLiveData<ViewResource<List<HomeUiItem>>>()
    val watchListResult = MutableLiveData<ViewResource<List<MovieViewParam>>>()
    val currentUserResult = MutableLiveData<ViewResource<UserViewParam>>()

    init {
        init(viewModelScope)
    }

    fun fetchHome() {
        viewModelScope.launch {
            getHomeFeedsUseCase().collect {
                homeFeedsResult.postValue(it)
            }
        }
    }

    fun fetchWatchlist() {
        viewModelScope.launch {
            getUserWatchListUseCase().collect {
                watchListResult.postValue(it)
            }
        }
    }

    fun getCurrentUser() {
        viewModelScope.launch {
            getCurrentUserUseCase().collect {
                currentUserResult.postValue(it)
            }
        }
    }
}