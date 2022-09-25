package com.arafat1419.armovies.core.ui.basic

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.arafat1419.armovies.core.databinding.ItemGenreBinding
import com.arafat1419.armovies.core.domain.model.GenreItemDomain
import com.google.android.material.card.MaterialCardView

class GenresAdapter : RecyclerView.Adapter<GenresAdapter.ViewHolder>() {
    private var listData = ArrayList<GenreItemDomain>()
    private var itemType: Int = 0

    var onItemClicked: ((genre: GenreItemDomain) -> Unit)? = null

    fun setData(newListData: List<GenreItemDomain>?, itemType: Int = 1) {
        if (newListData == null) return
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
            ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemsDataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data, position)
    }

    override fun getItemCount(): Int = listData.size

    override fun getItemViewType(position: Int): Int = itemType

    inner class ViewHolder(private val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GenreItemDomain, position: Int) {
            with(binding) {
                title.text = data.name

                val colorNumber = position % 4

                setBackgroundTint(
                    cardGenre, when (colorNumber) {
                        0 -> {
                            android.R.color.holo_blue_light
                        }
                        1 -> {
                            android.R.color.holo_red_light
                        }
                        2 -> {
                            android.R.color.holo_orange_light
                        }
                        else -> {
                            android.R.color.holo_green_light
                        }
                    }
                )

                itemView.setOnClickListener {
                    onItemClicked?.invoke(data)
                }
            }
        }

        private fun setBackgroundTint(card: MaterialCardView, color: Int) {
            card.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(itemView.context, color)
            )
        }
    }
}