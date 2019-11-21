package br.com.bank.commons.retrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RetrofitConsumer<T> : RetrofitExecutableInterface<T> {

    val retrofit: Retrofit = RetrofitFactory.create()
    private var api: Call<T>? = null
    private var retrofitCallback: RetrofitCallback<T>? = null

    fun setRetrofitCallback(retrofitCallback: RetrofitCallback<T>) {
        this.retrofitCallback = retrofitCallback
    }

    override fun setExecute(api: Call<T>) {
        this.api = api
    }

    fun run() {
        if (retrofitCallback == null) {
            throw NullPointerException("Callback == null")
        }

        api?.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.code() in 200..299) {
                    response.body()?.let { retrofitCallback?.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<T>, errorFail: Throwable) {
                //retrofitCallback!!.onError(emptyList<>())
                retrofitCallback?.onError(errorFail)
            }
        })
    }
}
