package com.example.home.domain

import android.view.View
import com.example.core.base.BaseUseCase
import com.example.core.wrapper.ViewResource
import com.example.home.data.network.repository.HomeRepository
import com.example.shared.data.model.mapper.MovieMapper
import com.example.shared.data.model.viewParam.MovieViewParam
import com.example.shared.utils.ext.suspendSubscribe
import com.example.shared.utils.mapper.Mapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class GetWatchListUseCase(
    val repository: HomeRepository,
    dispatcher: CoroutineDispatcher
): BaseUseCase<Nothing, List<MovieViewParam>>(dispatcher) {
    override suspend fun execute(param: Nothing?): Flow<ViewResource<List<MovieViewParam>>> = flow {
        emit(ViewResource.Loading())
        repository.fetchWatchList().collect {
            it.suspendSubscribe(
                doOnSuccess = { response ->
                    val movies = response.payload?.data
                    if (movies.isNullOrEmpty()) {
                        emit(ViewResource.Empty())
                    } else {
                        emit(ViewResource.Success(Mapper.ListMapper(MovieMapper).toViewParams(movies)))
                    }
                },
                doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                }
            )
        }
    }
}