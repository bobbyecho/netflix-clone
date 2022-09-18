package com.example.home.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.shared.data.model.viewParam.MovieViewParam
import com.example.styling.databinding.ItemMoviePosterBinding
import com.example.styling.databinding.ItemMoviePosterGridBinding

class MovieAdapter(
    private val isGridLayout: Boolean = false,
    private val itemClicked: (MovieViewParam) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val items = mutableListOf<MovieViewParam>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items : List<MovieViewParam>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PosterViewHolder).bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(!isGridLayout) {
            PosterViewHolderImpl(
                itemClicked,
                ItemMoviePosterBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            )
        } else {
            GridPosterViewHolderImpl(
                itemClicked,
                ItemMoviePosterGridBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            )
        }
    }
}

interface PosterViewHolder {
    fun bindView(item: MovieViewParam)
}

class PosterViewHolderImpl(
    private val itemClicked: (MovieViewParam) -> Unit,
    private val binding: ItemMoviePosterBinding,
): RecyclerView.ViewHolder(binding.root), PosterViewHolder {
    override fun bindView(item: MovieViewParam) {
        with(item) {
            binding.ivPoster.load(item.posterUrl)
            itemView.setOnClickListener { itemClicked.invoke(this) }
        }
    }
}

class GridPosterViewHolderImpl(
    private val itemClicked: (MovieViewParam) -> Unit,
    private val binding: ItemMoviePosterGridBinding
): RecyclerView.ViewHolder(binding.root), PosterViewHolder {
    override fun bindView(item: MovieViewParam) {
        with(item) {
            binding.ivPoster.load(item.posterUrl)
            itemView.setOnClickListener { itemClicked.invoke(this) }
        }
    }
}