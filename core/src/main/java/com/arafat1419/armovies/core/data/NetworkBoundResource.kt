package com.arafat1419.armovies.core.data

import com.arafat1419.armovies.core.data.remote.api.ApiResponse
import com.arafat1419.armovies.core.vo.Resource
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {
    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                emitAll(load(apiResponse.data).map { Resource.Success(it) })
            }
            is ApiResponse.Error -> {
                onFetchFailed()
                emit(Resource.Error(apiResponse.errorMessage))
            }
            else -> {}
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract suspend fun load(data: RequestType): Flow<ResultType>

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    fun asFlow(): Flow<Resource<ResultType>> = result
}