package com.arafat1419.armovies.core.domain.usecase

import androidx.paging.PagingData
import com.arafat1419.armovies.core.domain.model.*
import com.arafat1419.armovies.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface DataUseCase {
    fun getListGenre(): Flow<Resource<List<GenreItemDomain>>>
    fun getListPopular(): Flow<Resource<ListDomain<MovieDomain>>>
    fun getNowPlaying(): Flow<Resource<ListDomain<MovieDomain>>>
    fun getListByGenre(genreId: Int): Flow<Resource<ListDomain<MovieDomain>>>
    fun getMovieDetail(movieId: Int): Flow<Resource<MovieDomain>>
    fun getMovieTrailers(movieId: Int): Flow<Resource<List<TrailerDomain>>>
    fun getMovieReviews(movieId: Int): Flow<Resource<ListDomain<ReviewDomain>>>

    fun getListPopularPaging(): Flow<PagingData<MovieDomain>>
    fun getListByGenrePaging(genreId: Int): Flow<PagingData<MovieDomain>>
    fun getMovieReviewsPaging(movieId: Int): Flow<PagingData<ReviewDomain>>
}