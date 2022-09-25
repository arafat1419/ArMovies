package com.arafat1419.armovies.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class ReviewResponse(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("content")
	val content: String? = null
)