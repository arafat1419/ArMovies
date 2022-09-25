package com.arafat1419.armovies.core.data.remote

import com.arafat1419.armovies.core.data.remote.api.ApiResponse
import com.arafat1419.armovies.core.data.remote.api.ApiService
import com.arafat1419.armovies.core.data.remote.response.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getListGenre(): Flow<ApiResponse<List<GenreItemResponse>>> =
        flow {
            try {
                val response = apiService.getListGenre()

                if (response.genres != null) {
                    emit(ApiResponse.Success(response.genres))
                }

            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getListPopular(): Flow<ApiResponse<ListResponse<MovieResponse>>> =
        flow {
            try {
                val response = apiService.getListPopular()

                if (response.results != null) {
                    emit(ApiResponse.Success(response))
                }

            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getNowPlaying(): Flow<ApiResponse<ListResponse<MovieResponse>>> =
        flow {
            try {
                val response = apiService.getNowPlaying()

                if (response.results != null) {
                    emit(ApiResponse.Success(response))
                }

            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getListByGenre(genreId: Int): Flow<ApiResponse<ListResponse<MovieResponse>>> =
        flow {
            try {
                val response = apiService.getListByGenre(genreId)

                if (response.results != null) {
                    emit(ApiResponse.Success(response))
                }

            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getMovieDetail(movieId: Int): Flow<ApiResponse<MovieResponse>> =
        flow {
            try {
                val response = apiService.getMovieDetail(movieId)

                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getMovieTrailers(movieId: Int): Flow<ApiResponse<List<TrailerResponse>>> =
        flow {
            try {
                val response = apiService.getMovieTrailers(movieId)

                if (response.results != null) {
                    emit(ApiResponse.Success(response.results))
                }

            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getMovieReviews(movieId: Int): Flow<ApiResponse<ListResponse<ReviewResponse>>> =
        flow {
            try {
                val response = apiService.getMovieReviews(movieId)

                if (response.results != null) {
                    emit(ApiResponse.Success(response))
                }

            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

}