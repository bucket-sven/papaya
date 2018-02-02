package com.sunshine.papaya

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableAutoConfiguration
class PapayaApplication

fun main(args: Array<String>) {
    runApplication<PapayaApplication>(*args)
}
