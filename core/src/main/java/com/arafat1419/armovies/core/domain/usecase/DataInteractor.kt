package com.arafat1419.armovies.core.domain.usecase

import androidx.paging.PagingData
import com.arafat1419.armovies.core.domain.model.*
import com.arafat1419.armovies.core.domain.repository.IDataRepository
import com.arafat1419.armovies.core.domain.repository.IPagingRepository
import com.arafat1419.armovies.core.vo.Resource
import kotlinx.coroutines.flow.Flow

class DataInteractor(
    private val iDataRepository: IDataRepository,
    private val iPagingRepository: IPagingRepository
) : DataUseCase {
    override fun getListGenre(): Flow<Resource<List<GenreItemDomain>>> =
        iDataRepository.getListGenre()

    override fun getListPopular(): Flow<Resource<ListDomain<MovieDomain>>> =
        iDataRepository.getListPopular()

    override fun getNowPlaying(): Flow<Resource<ListDomain<MovieDomain>>> =
        iDataRepository.getNowPlaying()

    override fun getListByGenre(genreId: Int): Flow<Resource<ListDomain<MovieDomain>>> =
        iDataRepository.getListByGenre(genreId)

    override fun getMovieDetail(movieId: Int): Flow<Resource<MovieDomain>> =
        iDataRepository.getMovieDetail(movieId)

    override fun getMovieTrailers(movieId: Int): Flow<Resource<List<TrailerDomain>>> =
        iDataRepository.getMovieTrailers(movieId)

    override fun getMovieReviews(movieId: Int): Flow<Resource<ListDomain<ReviewDomain>>> =
        iDataRepository.getMovieReviews(movieId)

    override fun getListPopularPaging(): Flow<PagingData<MovieDomain>> =
        iPagingRepository.getListPopular()

    override fun getListByGenrePaging(genreId: Int): Flow<PagingData<MovieDomain>> =
        iPagingRepository.getListByGenre(genreId)

    override fun getMovieReviewsPaging(movieId: Int): Flow<PagingData<ReviewDomain>> =
        iPagingRepository.getMovieReviews(movieId)
}