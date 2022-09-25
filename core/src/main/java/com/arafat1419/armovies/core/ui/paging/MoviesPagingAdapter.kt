package com.arafat1419.armovies.core.ui.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arafat1419.armovies.core.databinding.ItemMovieBinding
import com.arafat1419.armovies.core.domain.model.MovieDomain
import com.arafat1419.armovies.core.utils.DataMapper
import com.bumptech.glide.Glide

class MoviesPagingAdapter :
    PagingDataAdapter<MovieDomain, MoviesPagingAdapter.ViewHolder>(MovieDiffCallBack) {

    var onItemClicked: ((movie: MovieDomain) -> Unit)? = null
    var onTrailerClicked: ((movie: MovieDomain) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ViewHolder(
        val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: MovieDomain) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(DataMapper.imageUrl + data.backdropPath)
                    .into(imgMovie)

                txtTitle.text = data.title
                txtRating.text = data.voteAverage.toString()

                btnTrailer.setOnClickListener {
                    onTrailerClicked?.invoke(data)
                }
                itemView.setOnClickListener {
                    onItemClicked?.invoke(data)
                }
            }
        }
    }

    companion object MovieDiffCallBack : DiffUtil.ItemCallback<MovieDomain>() {
        override fun areItemsTheSame(oldItem: MovieDomain, newItem: MovieDomain): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieDomain, newItem: MovieDomain): Boolean {
            return oldItem == newItem
        }
    }
}
