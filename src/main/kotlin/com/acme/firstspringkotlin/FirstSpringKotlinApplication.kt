package com.acme.firstspringkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FirstSpringKotlinApplication

fun main(args: Array<String>) {
	runApplication<FirstSpringKotlinApplication>(*args)
}
