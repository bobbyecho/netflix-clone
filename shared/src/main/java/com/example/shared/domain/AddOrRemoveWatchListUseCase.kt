package com.example.shared.domain

import com.example.core.base.BaseUseCase
import com.example.core.wrapper.DataResource
import com.example.core.wrapper.ViewResource
import com.example.shared.data.model.viewParam.MovieViewParam
import com.example.shared.data.repository.SharedApiRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class AddOrRemoveWatchListUseCase(
    val repository: SharedApiRepository,
    dispatcher: CoroutineDispatcher
): BaseUseCase<AddOrRemoveWatchListUseCase.Param, MovieViewParam>(
    dispatcher
) {
    data class Param(val movie: MovieViewParam)

    override suspend fun execute(param: Param?): Flow<ViewResource<MovieViewParam>> {
        param?.let {
            val movie = param.movie
            val movieId = movie.id
            val action = if(param.movie.isUserWatchlist)
                repository.removeWatchlist(movieId.toString())
            else
                repository.addWatchlist(movieId.toString())

            return action.map { result ->
                when(result){
                    is DataResource.Success -> ViewResource.Success(movie.apply {
                        isUserWatchlist = isUserWatchlist.not()
                    })
                    is DataResource.Error -> ViewResource.Error(result.exception)
                }
            }.onStart { emit(ViewResource.Loading()) }

        } ?: throw IllegalStateException("Param Required")
    }
}