package com.sunshine.papaya

import com.sunshine.papaya.util.ConstantUtil
import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableAutoConfiguration
@MapperScan(ConstantUtil.DAO_PACKAGE)
class PapayaApplication

fun main(args: Array<String>) {
    runApplication<PapayaApplication>(*args)
}
