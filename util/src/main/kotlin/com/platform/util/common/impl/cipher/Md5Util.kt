package com.platform.util.common.impl.cipher

import com.platform.util.common.CipherUtil
import com.platform.util.common.StringUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.math.BigInteger
import java.security.MessageDigest

@Component
class Md5Util: CipherUtil {

    private val ALGORITHM = "MD5"
    private val HASH = "0"

    @Autowired
    lateinit var stringUtil: StringUtil

    override fun encrypt(value: String?): String? {
        val md: MessageDigest = MessageDigest.getInstance(ALGORITHM)
        val digest: ByteArray = md.digest(stringUtil.nvl(value).toByteArray())
        val number = BigInteger(1, digest)
        var hashText = number.toString(16)
        while (hashText.length < 32) {
            hashText = HASH + hashText
        }
        return hashText
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