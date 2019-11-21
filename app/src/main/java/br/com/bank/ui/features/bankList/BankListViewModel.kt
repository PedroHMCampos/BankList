package br.com.bank.ui.features.bankList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.bank.data.bank.BankResponse
import br.com.bank.data.source.BankDataSource

class BankListViewModel(private val banDataSource: BankDataSource) : ViewModel() {

    private var _allBankList = MutableLiveData<MutableList<BankResponse>>()
    var allBankList = _allBankList

    private var _preferenceBankList = MutableLiveData<MutableList<BankResponse>>()
    var preferenceBankList = _preferenceBankList

    private var _otherBankList = MutableLiveData<MutableList<BankResponse>>()
    var otherBankList = _otherBankList

    fun getBankList() {
        banDataSource.listAllBank(
                object : BankDataSource.BankCallback {
                    override fun onSuccess(response: MutableList<BankResponse>) {
                        _allBankList.postValue(response)
                        _preferenceBankList.postValue(getPreferenceBankList(response))
                        _otherBankList.postValue(getOtherBankList(response))
                    }

                    override fun onError(errorResponse: Throwable) {
                    }
                }
        )
    }

    private fun getOtherBankList(response: MutableList<BankResponse>): MutableList<BankResponse>? {
        return response.filter {!it.favorite}.toMutableList()
    }

    private fun getPreferenceBankList(response: MutableList<BankResponse>): MutableList<BankResponse>? {
        return response.filter {it.favorite}.toMutableList()
    }

    fun getBankListFilter(
        bankList: MutableList<BankResponse>,
        textSearch: String
    ) {
        _otherBankList.postValue( getOtherBankList(bankList)?.filter { it.name.startsWith(textSearch, true) }?.toMutableList())
        _preferenceBankList.postValue( getPreferenceBankList(bankList)?.filter { it.name.startsWith(textSearch, true) }?.toMutableList())
    }
}