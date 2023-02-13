package com.acme.firstspringkotlin.repository.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.util.Date
import java.util.UUID

@Entity
class TransactionDBModel(
    val accountNumber: String,
    val amount: Double,
    val description: String?
) {
    @Id
    @GeneratedValue
    var id: UUID? = null
        private set

    val date: Date = Date()
}
