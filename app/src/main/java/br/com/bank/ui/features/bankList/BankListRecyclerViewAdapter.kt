package br.com.bank.ui.features.bankList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import br.com.bank.R
import br.com.bank.data.bank.BankResponse

class BankListRecyclerViewAdapter(var supporFragmentManager: FragmentManager) : RecyclerView.Adapter<BankListViewHolder>() {

    var bankList = mutableListOf<BankResponse>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankListViewHolder {
        val lp = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bank, null)
        view.layoutParams = lp
        return BankListViewHolder(view)
    }

    override fun getItemCount() = bankList.size

    override fun onBindViewHolder(holder: BankListViewHolder, position: Int) {
        holder.bind(bankList[position], supporFragmentManager)
    }

}