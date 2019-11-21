package br.com.bank.data.bank

import retrofit2.Call
import retrofit2.http.GET

interface BankApi {

    @GET("list")
    fun getListBank() : Call<MutableList<BankResponse>>

}