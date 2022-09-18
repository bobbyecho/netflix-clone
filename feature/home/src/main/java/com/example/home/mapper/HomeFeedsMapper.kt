package com.example.home.mapper

import com.example.home.data.network.model.response.HomeFeedsResponse
import com.example.home.data.network.model.response.SectionResponse
import com.example.home.presentation.viewparam.HomeFeedsViewParam
import com.example.home.presentation.viewparam.SectionViewParam
import com.example.shared.data.model.mapper.MovieMapper
import com.example.shared.utils.mapper.Mapper

object HomeFeedsMapper: Mapper.ViewParamMapper<HomeFeedsResponse, HomeFeedsViewParam> {
    override fun toViewParam(dataObject: HomeFeedsResponse?): HomeFeedsViewParam = HomeFeedsViewParam(
        MovieMapper.toViewParam(dataObject?.header),
        Mapper.ListMapper(SectionMapper).toViewParams(dataObject?.sections)
    )
}

object SectionMapper: Mapper.ViewParamMapper<SectionResponse, SectionViewParam> {
    override fun toViewParam(dataObject: SectionResponse?): SectionViewParam = SectionViewParam(
        sectionId = dataObject?.sectionId ?: -1,
        sectionName = dataObject?.sectionName.orEmpty(),
        contents = Mapper.ListMapper(MovieMapper).toViewParams(dataObject?.contents)
    )
}