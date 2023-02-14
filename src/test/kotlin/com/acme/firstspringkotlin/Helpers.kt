package com.acme.firstspringkotlin

import org.springframework.util.ResourceUtils

fun loadFile(fileName: String): String {
    return ResourceUtils.getFile("classpath:$fileName").readText()
}
