package com.arafat1419.armovies.di

import com.arafat1419.armovies.ui.detail.DetailViewModel
import com.arafat1419.armovies.ui.genres.GenresViewModel
import com.arafat1419.armovies.ui.home.HomeViewModel
import com.arafat1419.armovies.ui.movies.MoviesViewModel
import com.arafat1419.armovies.ui.trailer.TrailerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { MoviesViewModel(get()) }
    viewModel { GenresViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { TrailerViewModel(get()) }
}