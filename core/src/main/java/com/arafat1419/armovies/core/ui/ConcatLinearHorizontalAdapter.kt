package com.arafat1419.armovies.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arafat1419.armovies.core.databinding.ItemLinearHorizontalBinding
import com.arafat1419.armovies.core.domain.model.MovieDomain
import com.arafat1419.armovies.core.ui.basic.MoviesAdapter

class ConcatLinearHorizontalAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var adapter: MoviesAdapter? = null

    var onItemClicked: ((movie: MovieDomain) -> Unit)? = null
    var onTrailerClicked: ((movie: MovieDomain) -> Unit)? = null

    fun setAdapter(adapter: MoviesAdapter) {
        this.adapter = adapter
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemsDataBinding =
            ItemLinearHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemsDataBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val aHolder = holder as ViewHolder
        aHolder.bind(adapter)
    }

    override fun getItemCount(): Int = 1

    inner class ViewHolder(private val binding: ItemLinearHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(adapter: MoviesAdapter?) {
            binding.rvConcat.apply {
                layoutManager =
                    LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
                this.adapter = adapter
            }

            adapter?.onItemClicked = {
                onItemClicked?.invoke(it)
            }

            adapter?.onTrailerClicked = {
                onTrailerClicked?.invoke(it)
            }
        }
    }
}