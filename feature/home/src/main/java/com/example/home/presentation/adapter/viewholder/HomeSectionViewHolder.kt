package com.example.home.presentation.adapter.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.home.databinding.ItemSectionMovieBinding
import com.example.home.presentation.adapter.HomeAdapterClickListener
import com.example.home.presentation.adapter.MovieAdapter
import com.example.home.presentation.viewparam.homeitem.HomeUiItem

class HomeSectionViewHolder(
    private val binding: ItemSectionMovieBinding,
    private val recyclerViewPool: RecyclerView.RecycledViewPool,
    private val listener: HomeAdapterClickListener
): RecyclerView.ViewHolder(binding.root) {

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter {
            listener.onMovieClicked(it)
        }
    }

    fun bindView(item : HomeUiItem.MovieSectionItem){
        with(item){
            binding.tvTitleSection.text = this.sectionViewParam.sectionName
            movieAdapter.setItems(this.sectionViewParam.contents)
            binding.rvContents.apply {
                setRecycledViewPool(recyclerViewPool)
                adapter = movieAdapter
                layoutManager =
                    LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }
}