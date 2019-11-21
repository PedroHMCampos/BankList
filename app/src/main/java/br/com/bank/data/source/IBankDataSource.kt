package br.com.bank.data.source

interface IBankDataSource {

    fun listAllBank(bankCallback: BankDataSource.BankCallback)
}