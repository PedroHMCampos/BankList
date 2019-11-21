package br.com.bank.ui.features.bankList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.bank.R
import br.com.bank.commons.SimpleTextWatcher
import br.com.bank.data.bank.BankResponse
import kotlinx.android.synthetic.main.activity_bank_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BankListActivity : AppCompatActivity() {

    private val bankListViewModel: BankListViewModel by viewModel()
    var bankListLocal = mutableListOf<BankResponse>()

    private val recyclerOtherViewAdapter by lazy {
        BankListRecyclerViewAdapter(supportFragmentManager)
    }
    private val preferencesViewAdapter by lazy {
        BankListRecyclerViewAdapter(supportFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank_list)
        buildRecycler()
        bankListViewModel.getBankList()
        addTextWatcher()
        initObserver()
    }

    private fun addTextWatcher() {
        search_edit_text.addTextChangedListener(object : SimpleTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                bankListViewModel.getBankListFilter( bankListLocal, s.toString())
                super.onTextChanged(s, start, before, count)
            }
        })
    }

    private fun initObserver() {
        bankListViewModel.allBankList.observe(this, Observer {
            bankListLocal = it
        })
        bankListViewModel.preferenceBankList.observe(this, Observer {
            preferencesViewAdapter.bankList = it
        })
        bankListViewModel.otherBankList.observe(this, Observer {
            recyclerOtherViewAdapter.bankList = it
        })
    }

    private fun buildRecycler() {
        preference_banks_recycler.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = preferencesViewAdapter
            setHasFixedSize(false)
        }
        other_banks_recycler.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = recyclerOtherViewAdapter
            setHasFixedSize(false)
        }
    }
}
