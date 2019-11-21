package br.com.bank.commons

import br.com.bank.data.source.BankDataSource
import br.com.bank.ui.features.bankList.BankListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule= module {
    viewModel { BankListViewModel(get()) }
    single { BankDataSource() }
}