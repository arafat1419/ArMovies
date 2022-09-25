package com.arafat1419.armovies.core.utils

import android.util.Log
import android.view.View
import com.arafat1419.armovies.core.vo.Resource

class ResultHandler(
    private val tag: String,
    private val view: View
) {

    fun <T> handle(result: Resource<T>, isSuccess: (T?) -> Unit) {
        when (result) {
            is Resource.Error -> {
                Log.d(tag, result.message.toString())
            }
            is Resource.Loading -> {
                isLoading(true)
            }
            is Resource.Success -> {
                isLoading(false)
                isSuccess(result.data)
            }
        }
    }

    private fun isLoading(state: Boolean) {
        view.visibility = if (state) View.VISIBLE else View.GONE
    }
}