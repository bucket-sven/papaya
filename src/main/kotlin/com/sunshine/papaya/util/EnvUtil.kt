package com.sunshine.papaya.util

import org.springframework.beans.factory.annotation.Value

object EnvUtil {
    @Value(ConstantUtil.SPRING_PROFILE_ACTIVE)
    lateinit var env: String

    fun isDevelopment(): Boolean {
        return env == "dev"
    }

    fun isProduction(): Boolean {
        return env == "prod"
    }
}