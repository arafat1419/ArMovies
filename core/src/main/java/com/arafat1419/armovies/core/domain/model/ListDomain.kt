package com.arafat1419.armovies.core.domain.model

data class ListDomain<T>(
    val page: Int? = null,
    val results: List<T>? = null,
    val totalPages: Int? = null,
    val totalResults: Int? = null
)