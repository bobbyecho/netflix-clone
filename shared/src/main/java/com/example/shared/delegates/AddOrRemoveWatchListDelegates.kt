package com.example.shared.delegates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.wrapper.ViewResource
import com.example.shared.data.model.viewParam.MovieViewParam
import com.example.shared.domain.AddOrRemoveWatchListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

interface AddOrRemoveWatchListDelegates {
    fun init(scope: CoroutineScope)
    fun getWatchListResult(): LiveData<ViewResource<MovieViewParam>>
    fun addOrRemoveWatchList(movie: MovieViewParam)
}

class AddOrRemoveWatchlistDelegatesImpl: AddOrRemoveWatchListDelegates {
    private lateinit var coroutineScope: CoroutineScope
    private val useCase: AddOrRemoveWatchListUseCase by inject(AddOrRemoveWatchListUseCase::class.java)
    private val result = MutableLiveData<ViewResource<MovieViewParam>>()

    override fun init(scope: CoroutineScope) {
        coroutineScope = scope
    }

    override fun getWatchListResult(): LiveData<ViewResource<MovieViewParam>> {
        return result
    }

    override fun addOrRemoveWatchList(movie: MovieViewParam) {
        coroutineScope.launch {
            useCase(AddOrRemoveWatchListUseCase.Param(movie)).collect {
                result.postValue(it)
            }
        }
    }
}
