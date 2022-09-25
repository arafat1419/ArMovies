package com.arafat1419.armovies.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.arafat1419.armovies.core.domain.model.GenreItemDomain
import com.arafat1419.armovies.core.domain.model.ListDomain
import com.arafat1419.armovies.core.domain.model.MovieDomain
import com.arafat1419.armovies.core.domain.usecase.DataUseCase
import com.arafat1419.armovies.core.vo.Resource

class HomeViewModel(private val dataUseCase: DataUseCase): ViewModel() {
    fun getListGenre(): LiveData<Resource<List<GenreItemDomain>>> =
        dataUseCase.getListGenre().asLiveData()

    fun getListPopular(): LiveData<Resource<ListDomain<MovieDomain>>> =
        dataUseCase.getListPopular().asLiveData()

    fun getNowPlaying(): LiveData<Resource<ListDomain<MovieDomain>>> =
        dataUseCase.getNowPlaying().asLiveData()
}