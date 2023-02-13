package com.acme.firstspringkotlin.controller

import com.acme.firstspringkotlin.controller.model.CreateTransactionRequest
import com.acme.firstspringkotlin.controller.model.GetTransactionResponse
import com.acme.firstspringkotlin.controller.model.toDBModel
import com.acme.firstspringkotlin.controller.model.toTransactionResponse
import com.acme.firstspringkotlin.repository.TransferRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transfers")
class TransferController(
    val repository: TransferRepository
) {

    @PostMapping("/new")
    fun newTransfer (@RequestBody request: CreateTransactionRequest) {
        repository.save(request.toDBModel())
    }

    @GetMapping("/all")
    fun getAllTransfers(): List<GetTransactionResponse> {
        return repository.findAll().map { it.toTransactionResponse() }
    }

}
