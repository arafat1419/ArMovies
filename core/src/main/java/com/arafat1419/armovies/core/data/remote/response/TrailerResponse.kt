package com.arafat1419.armovies.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class TrailerResponse(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("site")
	val site: String? = null,

	@field:SerializedName("key")
	val key: String? = null
)
