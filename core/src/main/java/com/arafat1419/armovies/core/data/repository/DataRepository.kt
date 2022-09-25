package com.arafat1419.armovies.core.data.repository

import com.arafat1419.armovies.core.data.NetworkBoundResource
import com.arafat1419.armovies.core.data.remote.RemoteDataSource
import com.arafat1419.armovies.core.data.remote.api.ApiResponse
import com.arafat1419.armovies.core.data.remote.response.*
import com.arafat1419.armovies.core.domain.model.*
import com.arafat1419.armovies.core.domain.repository.IDataRepository
import com.arafat1419.armovies.core.utils.DataMapper
import com.arafat1419.armovies.core.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class DataRepository(
    private val remoteDataSource: RemoteDataSource
) : IDataRepository {
    override fun getListGenre(): Flow<Resource<List<GenreItemDomain>>> =
        object : NetworkBoundResource<List<GenreItemDomain>, List<GenreItemResponse>>() {
            override suspend fun load(data: List<GenreItemResponse>): Flow<List<GenreItemDomain>> =
                listOf(DataMapper.genreItemResponseToDomain(data)).asFlow()

            override suspend fun createCall(): Flow<ApiResponse<List<GenreItemResponse>>> =
                remoteDataSource.getListGenre()

        }.asFlow()

    override fun getListPopular(): Flow<Resource<ListDomain<MovieDomain>>> =
        object : NetworkBoundResource<ListDomain<MovieDomain>, ListResponse<MovieResponse>>() {
            override suspend fun load(data: ListResponse<MovieResponse>): Flow<ListDomain<MovieDomain>> =
                listOf(DataMapper.listMovieResponseToDomain(data)).asFlow()

            override suspend fun createCall(): Flow<ApiResponse<ListResponse<MovieResponse>>> =
                remoteDataSource.getListPopular()
        }.asFlow()

    override fun getNowPlaying(): Flow<Resource<ListDomain<MovieDomain>>> =
        object : NetworkBoundResource<ListDomain<MovieDomain>, ListResponse<MovieResponse>>() {
            override suspend fun load(data: ListResponse<MovieResponse>): Flow<ListDomain<MovieDomain>> =
                listOf(DataMapper.listMovieResponseToDomain(data)).asFlow()

            override suspend fun createCall(): Flow<ApiResponse<ListResponse<MovieResponse>>> =
                remoteDataSource.getNowPlaying()
        }.asFlow()

    override fun getListByGenre(genreId: Int): Flow<Resource<ListDomain<MovieDomain>>> =
        object : NetworkBoundResource<ListDomain<MovieDomain>, ListResponse<MovieResponse>>() {
            override suspend fun load(data: ListResponse<MovieResponse>): Flow<ListDomain<MovieDomain>> =
                listOf(DataMapper.listMovieResponseToDomain(data)).asFlow()

            override suspend fun createCall(): Flow<ApiResponse<ListResponse<MovieResponse>>> =
                remoteDataSource.getListByGenre(genreId)
        }.asFlow()

    override fun getMovieDetail(movieId: Int): Flow<Resource<MovieDomain>> =
        object : NetworkBoundResource<MovieDomain, MovieResponse>() {
            override suspend fun load(data: MovieResponse): Flow<MovieDomain> =
                listOf(DataMapper.movieItemResponseToDomain(data)).asFlow()

            override suspend fun createCall(): Flow<ApiResponse<MovieResponse>> =
                remoteDataSource.getMovieDetail(movieId)
        }.asFlow()

    override fun getMovieTrailers(movieId: Int): Flow<Resource<List<TrailerDomain>>> =
        object : NetworkBoundResource<List<TrailerDomain>, List<TrailerResponse>>() {
            override suspend fun load(data: List<TrailerResponse>): Flow<List<TrailerDomain>> =
                listOf(DataMapper.trailerResponseToDomain(data)).asFlow()

            override suspend fun createCall(): Flow<ApiResponse<List<TrailerResponse>>> =
                remoteDataSource.getMovieTrailers(movieId)
        }.asFlow()

    override fun getMovieReviews(movieId: Int): Flow<Resource<ListDomain<ReviewDomain>>> =
        object : NetworkBoundResource<ListDomain<ReviewDomain>, ListResponse<ReviewResponse>>() {
            override suspend fun load(data: ListResponse<ReviewResponse>): Flow<ListDomain<ReviewDomain>> =
                listOf(DataMapper.listReviewResponseToDomain(data)).asFlow()

            override suspend fun createCall(): Flow<ApiResponse<ListResponse<ReviewResponse>>> =
                remoteDataSource.getMovieReviews(movieId)
        }.asFlow()

}