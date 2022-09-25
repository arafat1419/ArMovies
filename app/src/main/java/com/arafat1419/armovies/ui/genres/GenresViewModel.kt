package com.arafat1419.armovies.ui.genres

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.arafat1419.armovies.core.domain.model.GenreItemDomain
import com.arafat1419.armovies.core.domain.usecase.DataUseCase
import com.arafat1419.armovies.core.vo.Resource

class GenresViewModel(private val dataUseCase: DataUseCase): ViewModel() {
    fun getListGenre(): LiveData<Resource<List<GenreItemDomain>>> =
        dataUseCase.getListGenre().asLiveData()
}