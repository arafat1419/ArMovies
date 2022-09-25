package com.arafat1419.armovies.core.domain.repository

import androidx.paging.PagingData
import com.arafat1419.armovies.core.domain.model.MovieDomain
import com.arafat1419.armovies.core.domain.model.ReviewDomain
import kotlinx.coroutines.flow.Flow

interface IPagingRepository {
    fun getListPopular(): Flow<PagingData<MovieDomain>>
    fun getListByGenre(genreId: Int): Flow<PagingData<MovieDomain>>
    fun getMovieReviews(movieId: Int): Flow<PagingData<ReviewDomain>>
}