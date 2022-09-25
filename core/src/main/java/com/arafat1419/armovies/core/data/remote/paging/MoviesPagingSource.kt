package com.arafat1419.armovies.core.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arafat1419.armovies.core.data.remote.api.ApiService
import com.arafat1419.armovies.core.data.remote.response.MovieResponse
import com.arafat1419.armovies.core.utils.Helper
import retrofit2.HttpException
import java.io.IOException

class MoviesPagingSource(
    private val apiService: ApiService,
    private val type: String,
    private val genreId: Int = 0
) :
    PagingSource<Int, MovieResponse>() {
    override fun getRefreshKey(state: PagingState<Int, MovieResponse>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponse> {
        val pageIndex = params.key ?: 1
        return try {
            val response = when (type) {
                Helper.IS_GET_POPULAR -> apiService.getListPopular(pageIndex)
                else -> {
                    apiService.getListByGenre(genreId, pageIndex)
                }
            }

            val movies = response.results

            val nextKey =
                if (movies.isNullOrEmpty()) {
                    null
                } else {
                    if (
                        params.loadSize == 3 * 20
                    ) {
                        pageIndex + 1
                    } else {
                        pageIndex + (params.loadSize / 20)
                    }
                }

            LoadResult.Page(
                data = movies!!,
                prevKey = if (pageIndex == 1) null else pageIndex - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}