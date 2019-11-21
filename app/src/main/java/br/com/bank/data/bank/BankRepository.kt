package br.com.bank.data.bank

import br.com.bank.data.source.BankDataSource
import br.com.bank.data.source.IBankDataSource

class BankRepository(var dataSource: IBankDataSource) : IBankDataSource {

    override fun listAllBank(bankCallback: BankDataSource.BankCallback) {
        dataSource.listAllBank(bankCallback)
    }
}