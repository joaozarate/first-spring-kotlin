package com.acme.firstspringkotlin.controller

import com.acme.firstspringkotlin.repository.TransferRepository
import com.acme.firstspringkotlin.repository.model.TransactionDBModel
import com.acme.firstspringkotlin.v1.CreateTransactionRequest
import com.acme.firstspringkotlin.v1.ListTransactionsResponse
import com.google.protobuf.util.JsonFormat
import com.google.type.Money
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Calendar
import java.util.Date

import com.google.type.Date as DateProto

@RestController
@RequestMapping("/transfers")
class TransferController(
    val repository: TransferRepository
) {

    @PostMapping
    fun create (@RequestBody request: String) {
        CreateTransactionRequest.newBuilder().apply {
            JsonFormat.parser().ignoringUnknownFields().merge(request, this)
        }.build().also {
            repository.save(it.fromProto())
        }
    }

    @GetMapping
    fun list(): String {
        return ListTransactionsResponse.newBuilder().apply {
            addAllTransactions(repository.findAll().map { it.toProto() })
        }.build().let {
            JsonFormat.printer().print(it)
        }
    }

}

fun CreateTransactionRequest.fromProto(): TransactionDBModel {
    return TransactionDBModel(
        accountNumber = targetAccount,
        amount = value.toDouble(),
        description = description
    )
}

fun TransactionDBModel.toProto() = ListTransactionsResponse.Transaction.newBuilder().also {
    it.id = id.toString()
    it.value = amount.toProto()
    it.targetAccount = accountNumber
    it.targetAccount = accountNumber
    it.date = date.toProto()
}.build()

fun Money.toDouble(): Double {
    val integerPart = this.units.toBigDecimal()
    val decimalPart = this.nanos.toBigDecimal().scaleByPowerOfTen(-2).stripTrailingZeros()
    return (integerPart + decimalPart).toDouble()
}

fun Double.toProto(): Money {
    return Money.newBuilder().also {
        it.units = 1
        it.nanos = 1
    }.build()
}

fun Date.toProto(): DateProto {
    return DateProto.newBuilder().also {
        val date = Calendar.getInstance()
        date.time = this
        it.day = date.get(Calendar.DAY_OF_MONTH)
        it.month = date.get(Calendar.MONTH)
        it.year = date.get(Calendar.YEAR)
    }.build()
}
