package com.example.shared.data.model.mapper

import com.example.shared.data.model.response.MovieResponse
import com.example.shared.data.model.viewParam.MovieViewParam
import com.example.shared.utils.Mapper

object MovieMapper: Mapper.ViewParamMapper<MovieResponse, MovieViewParam> {
    override fun toViewParam(dataObject: MovieResponse?): MovieViewParam = MovieViewParam(
        cast = dataObject?.cast.orEmpty(),
        category = dataObject?.category.orEmpty(),
        director = dataObject?.director.orEmpty(),
        filmRate = dataObject?.filmRate.orEmpty(),
        id = dataObject?.id ?: -1,
        isUserWatchlist = dataObject?.isUserWatchlist ?: false,
        overview = dataObject?.overview.orEmpty(),
        posterUrl = dataObject?.posterUrl.orEmpty(),
        releaseDate = dataObject?.releaseDate.orEmpty(),
        title = dataObject?.title.orEmpty(),
        trailerUrl = dataObject?.trailerUrl.orEmpty(),
        videoUrl = dataObject?.videoUrl.orEmpty(),
        runtime = dataObject?.runtime ?: -1,
    )
}