package com.arafat1419.armovies.core.ui.basic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arafat1419.armovies.core.databinding.ItemTitleBinding

class TitleAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var title: String = ""
    private var isShowAll: Boolean = true
    private var itemType: Int = 0

    var onShowClicked: ((title: String) -> Unit)? = null

    fun setData(title: String, isShowAll: Boolean, itemType: Int = 1) {
        this.title = title
        this.itemType = itemType
        this.isShowAll = isShowAll
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val itemsDataBinding =
            ItemTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemsDataBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val aHolder = holder as ViewHolder
        aHolder.bind(title, isShowAll)
    }

    override fun getItemCount(): Int = 1

    override fun getItemViewType(position: Int): Int = itemType

    inner class ViewHolder(private val binding: ItemTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dataTitle: String, dataIsShowAll: Boolean) {
            with(binding) {
                txtShowAll.setOnClickListener {
                    onShowClicked?.invoke(dataTitle)
                }
                
                txtTitle.text = dataTitle

                txtShowAll.visibility = if (dataIsShowAll) View.VISIBLE else View.GONE
            }
        }
    }
}