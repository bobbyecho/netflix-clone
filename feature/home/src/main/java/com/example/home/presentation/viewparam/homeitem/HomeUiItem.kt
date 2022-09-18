package com.example.home.presentation.viewparam.homeitem

import com.example.home.presentation.viewparam.SectionViewParam
import com.example.shared.data.model.viewParam.MovieViewParam

sealed class HomeUiItem {
    class HeaderSectionItem(val movieViewParam: MovieViewParam): HomeUiItem()
    class MovieSectionItem(val sectionViewParam: SectionViewParam): HomeUiItem()
}
