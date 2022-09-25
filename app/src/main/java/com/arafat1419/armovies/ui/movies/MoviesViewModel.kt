package com.arafat1419.armovies.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.arafat1419.armovies.core.domain.model.MovieDomain
import com.arafat1419.armovies.core.domain.usecase.DataUseCase
import kotlinx.coroutines.flow.Flow

class MoviesViewModel(private val dataUseCase: DataUseCase) : ViewModel() {
    fun getListPopularPaging(): Flow<PagingData<MovieDomain>> =
        dataUseCase.getListPopularPaging().cachedIn(viewModelScope)

    fun getListByGenrePaging(genreId: Int): Flow<PagingData<MovieDomain>> =
        dataUseCase.getListByGenrePaging(genreId).cachedIn(viewModelScope)
}