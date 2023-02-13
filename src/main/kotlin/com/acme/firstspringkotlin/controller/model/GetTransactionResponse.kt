package com.acme.firstspringkotlin.controller.model

import com.acme.firstspringkotlin.repository.model.TransactionDBModel
import java.util.Date

class GetTransactionResponse(
    val targetAccount: String,
    val value: Double,
    val description: String,
    val date: Date,
    val id: String
)

fun TransactionDBModel.toTransactionResponse() = GetTransactionResponse(
    targetAccount = accountNumber,
    value = amount,
    description = description.orEmpty(),
    date = date,
    id = id.toString()
)
