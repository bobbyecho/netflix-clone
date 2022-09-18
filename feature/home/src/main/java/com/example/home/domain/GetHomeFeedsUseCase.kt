package com.example.home.domain

import com.example.core.base.BaseUseCase
import com.example.core.wrapper.ViewResource
import com.example.home.data.network.repository.HomeRepository
import com.example.home.mapper.SectionMapper
import com.example.home.presentation.viewparam.homeitem.HomeUiItem
import com.example.shared.data.model.mapper.MovieMapper
import com.example.shared.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class GetHomeFeedsUseCase(
    private val repository: HomeRepository,
    dispatcher: CoroutineDispatcher
): BaseUseCase<Nothing, List<HomeUiItem>>(dispatcher) {
    override suspend fun execute(param: Nothing?): Flow<ViewResource<List<HomeUiItem>>> = flow {
        emit(ViewResource.Loading())
        repository.fetchHomeFeeds().collect {
            it.suspendSubscribe(
                doOnSuccess = { result ->
                    val data = mutableListOf<HomeUiItem>()
                    result.payload?.data?.let { homeData ->
                        homeData.header?.let { movie ->
                            data.add(HomeUiItem.HeaderSectionItem(MovieMapper.toViewParam(movie)))
                        }
                        homeData.sections?.forEach { section ->
                            data.add(HomeUiItem.MovieSectionItem(SectionMapper.toViewParam(section)))
                        }
                    }
                    emit(ViewResource.Success(data))
                },
                doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                },
            )
        }
    }
}