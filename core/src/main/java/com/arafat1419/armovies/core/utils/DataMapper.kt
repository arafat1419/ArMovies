package com.arafat1419.armovies.core.utils

import com.arafat1419.armovies.core.BuildConfig
import com.arafat1419.armovies.core.data.remote.response.*
import com.arafat1419.armovies.core.domain.model.*

object DataMapper {
    const val imageUrl = BuildConfig.BASE_URL_IMAGE

    fun genreItemResponseToDomain(input: List<GenreItemResponse>): List<GenreItemDomain> =
        input.map {
            genreItemResponseToDomain(it)
        }

    fun genreItemResponseToDomain(input: GenreItemResponse): GenreItemDomain =
        GenreItemDomain(
            id = input.id,
            name = input.name
        )

    fun listMovieResponseToDomain(input: ListResponse<MovieResponse>): ListDomain<MovieDomain> =
        ListDomain(
            page = input.page,
            results = input.results?.map {
                movieItemResponseToDomain(it)
            },
            totalPages = input.totalPages,
            totalResults = input.totalResults
        )

    fun movieItemResponseToDomain(input: MovieResponse): MovieDomain =
        MovieDomain(
            id = input.id,
            title = input.title,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            overview = input.overview,
            voteAverage = input.voteAverage,
            releaseDate = input.releaseDate
        )

    fun trailerResponseToDomain(input: List<TrailerResponse>): List<TrailerDomain> =
        input.map {
            trailerResponseToDomain(it)
        }

    fun trailerResponseToDomain(input: TrailerResponse): TrailerDomain =
        TrailerDomain(
            id = input.id,
            type = input.type,
            site = input.site,
            key = input.key
        )

    fun listReviewResponseToDomain(input: ListResponse<ReviewResponse>): ListDomain<ReviewDomain> =
        ListDomain(
            page = input.page,
            results = input.results?.map {
                reviewItemResponseToDomain(it)
            },
            totalPages = input.totalPages,
            totalResults = input.totalResults
        )

    fun reviewItemResponseToDomain(input: ReviewResponse): ReviewDomain =
        ReviewDomain(
            id = input.id,
            author = input.author,
            content = input.content
        )
}