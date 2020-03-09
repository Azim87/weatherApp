package com.example.wheatherapp.base

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


abstract class BaseRetrofitCallback<T> : Callback<T> {
    abstract fun onSuccess(results: T?)

    abstract fun onFailure(e: Exception?)

    override fun onResponse(call: Call<T>, response: Response<T>) {
        onSuccess(response.body())
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        onFailure(Exception(t));
    }
}