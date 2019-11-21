package br.com.bank.ui.features.bankList

import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import br.com.bank.R
import br.com.bank.data.bank.BankResponse
import br.com.bank.ui.features.bankList.detail.DetailBankFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_bank.view.*

class BankListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(
        bankResponse: BankResponse,
        supporFragmentManager: FragmentManager
    ) {
        itemView.bank_name_text.text = """${bankResponse.code} - ${bankResponse.name}"""
        Glide.with(itemView.context)
            .load(bankResponse.image)
            .error(R.drawable.ic_bank_default)
            .into(itemView.bank_image)
        itemView.arrow_detail_bank_image.setOnClickListener {
            DetailBankFragment().setBankResponse(bankResponse).show(supporFragmentManager, "DetailBankFragment")
        }
    }
}