package com.arafat1419.armovies.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.arafat1419.armovies.core.data.remote.api.ApiService
import com.arafat1419.armovies.core.data.remote.paging.MoviesPagingSource
import com.arafat1419.armovies.core.data.remote.paging.ReviewsPagingSource
import com.arafat1419.armovies.core.domain.model.MovieDomain
import com.arafat1419.armovies.core.domain.model.ReviewDomain
import com.arafat1419.armovies.core.domain.repository.IPagingRepository
import com.arafat1419.armovies.core.utils.DataMapper
import com.arafat1419.armovies.core.utils.Helper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PagingRepository(
    private val apiService: ApiService
) : IPagingRepository {
    override fun getListPopular(): Flow<PagingData<MovieDomain>> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                MoviesPagingSource(apiService, Helper.IS_GET_POPULAR)
            }
        ).flow.map { pagingData ->
            pagingData.map {
                DataMapper.movieItemResponseToDomain(it)
            }
        }

    override fun getListByGenre(genreId: Int): Flow<PagingData<MovieDomain>> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                MoviesPagingSource(apiService, Helper.IS_GET_BY_GENRE, genreId)
            }
        ).flow.map { pagingData ->
            pagingData.map {
                DataMapper.movieItemResponseToDomain(it)
            }
        }

    override fun getMovieReviews(movieId: Int): Flow<PagingData<ReviewDomain>> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                ReviewsPagingSource(apiService, movieId)
            }
        ).flow.map { pagingData ->
            pagingData.map {
                DataMapper.reviewItemResponseToDomain(it)
            }
        }
}