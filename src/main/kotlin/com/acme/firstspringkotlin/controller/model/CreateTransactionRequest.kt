package com.acme.firstspringkotlin.controller.model

import com.acme.firstspringkotlin.repository.model.TransactionDBModel

class CreateTransactionRequest (
    val targetAccount: String,
    val value: Double,
    val description: String?
)

fun CreateTransactionRequest.toDBModel() = TransactionDBModel(
    accountNumber =  targetAccount,
    amount = value,
    description = description
)
