package com.arafat1419.armovies.core.ui.basic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arafat1419.armovies.core.databinding.ItemReviewBinding
import com.arafat1419.armovies.core.domain.model.ReviewDomain

class ReviewsAdapter : RecyclerView.Adapter<ReviewsAdapter.ViewHolder>() {
    private var listData = ArrayList<ReviewDomain>()
    private var itemType = 0

    fun setData(newListData: List<ReviewDomain>?, itemType: Int = 1) {
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
            ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemsDataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

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
}