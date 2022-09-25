package com.arafat1419.armovies.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.arafat1419.armovies.core.domain.model.MovieDomain
import com.arafat1419.armovies.core.domain.model.ReviewDomain
import com.arafat1419.armovies.core.domain.usecase.DataUseCase
import com.arafat1419.armovies.core.vo.Resource
import kotlinx.coroutines.flow.Flow

class DetailViewModel(private val dataUseCase: DataUseCase) : ViewModel() {
    fun getMovieDetail(movieId: Int): LiveData<Resource<MovieDomain>> =
        dataUseCase.getMovieDetail(movieId).asLiveData()

    fun getMovieReviewsPaging(movieId: Int): Flow<PagingData<ReviewDomain>> =
        dataUseCase.getMovieReviewsPaging(movieId).cachedIn(viewModelScope)
}