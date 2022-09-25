package com.arafat1419.armovies.core.utils

import androidx.recyclerview.widget.RecyclerView

object Helper {
    const val IS_GET_POPULAR = "isGetPopular"
    const val IS_GET_BY_GENRE = "isGetByGenre"

    fun setRecyclerView(recyclerView: RecyclerView, layoutManager: RecyclerView.LayoutManager, adapter: RecyclerView.Adapter<*>){
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}