package com.example.home.presentation.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.home.databinding.ItemHeaderHomeBinding
import com.example.home.presentation.adapter.HomeAdapterClickListener
import com.example.home.presentation.viewparam.homeitem.HomeUiItem
import com.example.shared.data.model.viewParam.MovieViewParam
import com.example.shared.utils.CommonUtils

class HomeHeaderViewHolder(
    private val binding: ItemHeaderHomeBinding,
    private val listener: HomeAdapterClickListener
): RecyclerView.ViewHolder(binding.root) {
    fun bindView(item: HomeUiItem.HeaderSectionItem) {
        with(item.movieViewParam) {
            binding.tvAddToWatchlistHeader.setCompoundDrawablesWithIntrinsicBounds(
                0,
                CommonUtils.getWatchlistIcon(isUserWatchlist), 0, 0
            )
            binding.ivHeaderMovie.load(this.posterUrl)
            binding.tvTitleMovie.text = this.title
            binding.tvInfoHeader.setOnClickListener {
                listener.onMovieClicked(this)
            }
            binding.tvAddToWatchlistHeader.setOnClickListener {
                listener.onMyListClicked(this)
            }
            binding.cvPlayHeader.setOnClickListener {
                listener.onPlayMovieClicked(this)
            }
        }
    }
}