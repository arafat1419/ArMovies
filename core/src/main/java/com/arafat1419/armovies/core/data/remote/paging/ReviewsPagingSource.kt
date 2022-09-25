package com.arafat1419.armovies.core.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arafat1419.armovies.core.data.remote.api.ApiService
import com.arafat1419.armovies.core.data.remote.response.ReviewResponse
import retrofit2.HttpException
import java.io.IOException

class ReviewsPagingSource(private val apiService: ApiService, private val movieId: Int) :
    PagingSource<Int, ReviewResponse>() {
    override fun getRefreshKey(state: PagingState<Int, ReviewResponse>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReviewResponse> {
        val pageIndex = params.key ?: 1
        return try {
            val response = apiService.getMovieReviews(movieId, pageIndex)

            val reviews = response.results

            val nextKey =
                if (reviews.isNullOrEmpty()) {
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
                data = reviews!!,
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