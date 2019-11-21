package br.com.bank.data.source

import br.com.bank.commons.retrofit.RetrofitCallback
import br.com.bank.commons.retrofit.RetrofitConsumer
import br.com.bank.data.bank.BankApi
import br.com.bank.data.bank.BankResponse

class BankDataSource : IBankDataSource {

    override fun listAllBank(bankCallback: BankCallback) {
        try {
            val retrofitConsumer = RetrofitConsumer<MutableList<BankResponse>>()
            val bankApi = retrofitConsumer.retrofit.create(BankApi::class.java)
            retrofitConsumer.setExecute(bankApi.getListBank())
            retrofitConsumer.setRetrofitCallback(bankCallback)
            retrofitConsumer.run()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    interface BankCallback : RetrofitCallback<MutableList<BankResponse>>
}