package com.arafat1419.armovies.core.data.remote.api

import com.arafat1419.armovies.core.BuildConfig
import com.arafat1419.armovies.core.data.remote.response.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("genre/movie/list")
    suspend fun getListGenre(
        @Query("api_key") apiKey: String = API_KEY
    ): GenresResponse

    @GET("movie/popular")
    suspend fun getListPopular(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = API_KEY
    ): ListResponse<MovieResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") apiKey: String = API_KEY
    ): ListResponse<MovieResponse>

    @GET("discover/movie")
    suspend fun getListByGenre(
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = API_KEY
    ): ListResponse<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieTrailers(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): ListResponse<TrailerResponse>

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = API_KEY
    ): ListResponse<ReviewResponse>

    companion object {
        private const val API_KEY = BuildConfig.API_KEY
    }
}