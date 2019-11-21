package br.com.bank.commons.retrofit

interface RetrofitCallback<T> {

    fun onSuccess(response: T)

    fun onError(errorResponse: Throwable)
}
