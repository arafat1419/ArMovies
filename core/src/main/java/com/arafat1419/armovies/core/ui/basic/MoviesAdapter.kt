package com.arafat1419.armovies.core.ui.basic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arafat1419.armovies.assets.R
import com.arafat1419.armovies.core.databinding.ItemMovieBinding
import com.arafat1419.armovies.core.domain.model.MovieDomain
import com.arafat1419.armovies.core.utils.DataMapper
import com.bumptech.glide.Glide

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    private var listData = ArrayList<MovieDomain>()
    private var isShowPosition = false
    private var itemType: Int = 0

    var onItemClicked: ((movie: MovieDomain) -> Unit)? = null
    var onTrailerClicked: ((movie: MovieDomain) -> Unit)? = null

    fun setData(newListData: List<MovieDomain>?, isShowPosition: Boolean, itemType: Int = 1) {
        if (newListData == null) return
        this.isShowPosition = isShowPosition
        this.itemType = itemType
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemsDataBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemsDataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data, position)
    }

    override fun getItemCount(): Int = listData.size

    override fun getItemViewType(position: Int): Int = itemType

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MovieDomain, position: Int) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(DataMapper.imageUrl + data.backdropPath)
                    .into(imgMovie)

                txtTitle.text = data.title
                txtRating.text = data.voteAverage.toString()

                if (isShowPosition) {
                    txtPosition.text = itemView.resources.getString(
                        R.string.format_movie_position,
                        (position + 1),
                        (listData.size)
                    )
                }

                btnTrailer.setOnClickListener {
                    onTrailerClicked?.invoke(data)
                }
                itemView.setOnClickListener {
                    onItemClicked?.invoke(data)
                }
            }
        }
    }
}