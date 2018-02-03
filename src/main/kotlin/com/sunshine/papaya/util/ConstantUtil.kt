package com.sunshine.papaya.util

object ConstantUtil {
    const val CONTROLLER_ASPECT = "execution(* com.sunshine.papaya.controller.*.*(..))"
    const val SPRING_PROFILE_ACTIVE = "\${spring.profiles.active}"
    const val ERROR_PATH = "/custom/error"
    const val DAO_PACKAGE = "com.sunshine.papaya.dao"
}