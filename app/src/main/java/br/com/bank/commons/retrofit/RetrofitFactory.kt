package br.com.bank.commons.retrofit

import br.com.bank.commons.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object RetrofitFactory {

    fun create(): Retrofit {
        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor { chain ->
            val original = chain.request()

            val request = original.newBuilder()
            request.header("Content-Type", "application/json")
            request.header("Accept", "application/json")
            request.method(original.method(), original.body())
            chain.proceed(request.build())
        }

        val client = httpClient.build()
        val gson = GsonBuilder().setLenient().create()

        return Retrofit.Builder()
                .baseUrl(Constants.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
    }
}
