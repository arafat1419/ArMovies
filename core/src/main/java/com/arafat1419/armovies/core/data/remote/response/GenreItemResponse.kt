package com.arafat1419.armovies.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class GenreItemResponse(
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("name")
    val name: String? = null
)
