package com.platform.util.common

interface CipherUtil {

    @Throws(Exception::class)
    fun encrypt(value: String?): String?

    @Throws(Exception::class)
    fun encrypt(value: String?, key: String?): String?

    @Throws(Exception::class)
    fun decrypt(data: String?): String?

    @Throws(Exception::class)
    fun decrypt(data: String?, key: String?): String?

}