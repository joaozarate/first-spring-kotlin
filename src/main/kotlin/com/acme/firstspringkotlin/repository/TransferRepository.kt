package com.acme.firstspringkotlin.repository

import com.acme.firstspringkotlin.repository.model.TransactionDBModel
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface TransferRepository: CrudRepository<TransactionDBModel, UUID>
