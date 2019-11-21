package br.com.bank.data.bank

data class BankResponse(
    val code: String,
    val favorite: Boolean,
    val image: String,
    val imageName: String,
    val name: String
)