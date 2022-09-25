package com.arafat1419.armovies.ui.trailer

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.arafat1419.armovies.core.domain.model.TrailerDomain
import com.arafat1419.armovies.core.domain.usecase.DataUseCase
import com.arafat1419.armovies.core.vo.Resource

class TrailerViewModel(private val dataUseCase: DataUseCase) : ViewModel() {
    fun getMovieTrailers(movieId: Int): LiveData<Resource<List<TrailerDomain>>> =
        dataUseCase.getMovieTrailers(movieId).asLiveData()
}