package br.com.bank.ui.features.bankList.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.bank.R
import br.com.bank.data.bank.BankResponse
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_detail_bank.*

class DetailBankFragment : BottomSheetDialogFragment() {

    private var bankResponse : BankResponse? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_bank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setDataDetail(bankResponse)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setDataDetail(bankResponse: BankResponse?) {
        activity?.let { Glide.with(it).load(bankResponse?.image).error(R.drawable.ic_bank_default).into(detail_bank_image) }
        detail_bank_name_text.text = bankResponse?.name
    }

    fun setBankResponse(bankResponse: BankResponse) : BottomSheetDialogFragment {
        this.bankResponse = bankResponse
        return this
    }
}
