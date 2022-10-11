package com.platform.util.common.impl.cipher

import com.platform.util.common.CipherUtil
import com.platform.util.common.StringUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

@Component
class SHA256Util: CipherUtil {

    private val ALGORITHM = "SHA-256"
    private val UTF8 = "UTF-8"
    private val SALT = "inZhQ0Hw0t"

    @Autowired
    lateinit var stringUtil: StringUtil

    override fun encrypt(value: String?): String {
        val digest = MessageDigest.getInstance(ALGORITHM)
        val hash = digest.digest((stringUtil.nvl(value) + SALT).toByteArray(charset(UTF8)))
        return String(Base64.getEncoder().encode(hash))
    }

    override fun encrypt(value: String?, key: String?): String? {
        throw Exception("Unsupported Cipher")
    }

    override fun decrypt(data: String?): String? {
        throw Exception("Unsupported Cipher")
    }

    override fun decrypt(data: String?, key: String?): String? {
        throw Exception("Unsupported Cipher")
    }

}