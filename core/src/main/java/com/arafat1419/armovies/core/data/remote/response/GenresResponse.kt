package com.arafat1419.armovies.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @field:SerializedName("genres")
    val genres: List<GenreItemResponse>? = null
)
