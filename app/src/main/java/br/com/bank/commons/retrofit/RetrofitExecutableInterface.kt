package br.com.bank.commons.retrofit

import retrofit2.Call

internal interface RetrofitExecutableInterface<T> {

    fun setExecute(api: Call<T>)
}
