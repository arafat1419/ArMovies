package com.arafat1419.armovies.core.domain.repository

import com.arafat1419.armovies.core.domain.model.*
import com.arafat1419.armovies.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface IDataRepository {
    fun getListGenre(): Flow<Resource<List<GenreItemDomain>>>
    fun getListPopular(): Flow<Resource<ListDomain<MovieDomain>>>
    fun getNowPlaying(): Flow<Resource<ListDomain<MovieDomain>>>
    fun getListByGenre(genreId: Int): Flow<Resource<ListDomain<MovieDomain>>>
    fun getMovieDetail(movieId: Int): Flow<Resource<MovieDomain>>
    fun getMovieTrailers(movieId: Int): Flow<Resource<List<TrailerDomain>>>
    fun getMovieReviews(movieId: Int): Flow<Resource<ListDomain<ReviewDomain>>>
}