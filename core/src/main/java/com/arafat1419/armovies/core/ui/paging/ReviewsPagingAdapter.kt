package com.arafat1419.armovies.core.ui.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arafat1419.armovies.core.databinding.ItemReviewBinding
import com.arafat1419.armovies.core.domain.model.ReviewDomain

class ReviewsPagingAdapter :
    PagingDataAdapter<ReviewDomain, ReviewsPagingAdapter.ViewHolder>(ReviewDiffCallBack) {

    private var itemType = 0

    fun setItemType(itemType: Int = 1) {
        this.itemType = itemType
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemReviewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun getItemViewType(position: Int): Int = itemType

    inner class ViewHolder(private val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ReviewDomain) {
            with(binding) {
                txtAuthor.text = data.author
                txtContent.text = data.content
            }
        }
    }

    companion object ReviewDiffCallBack : DiffUtil.ItemCallback<ReviewDomain>() {
        override fun areItemsTheSame(oldItem: ReviewDomain, newItem: ReviewDomain): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ReviewDomain, newItem: ReviewDomain): Boolean {
            return oldItem == newItem
        }
    }
}