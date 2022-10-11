package com.platform.util.common.impl.cipher

import com.platform.util.common.CipherUtil
import com.platform.util.common.StringUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

@Component
class AES128Util: CipherUtil {

    private val ALGORITHM = "AES"

    @Autowired
    lateinit var stringUtil: StringUtil

    override fun encrypt(value: String?): String? {
        throw Exception("Unsupported Cipher")
    }

    override fun encrypt(value: String?, key: String?): String? {
        val cipherKey: Key = SecretKeySpec(stringUtil.nvl(key).toByteArray(), ALGORITHM);
        val cipher: Cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.ENCRYPT_MODE, cipherKey)
        return String(Base64.getEncoder().encode(cipher.doFinal(stringUtil.nvl(value).toByteArray())))
    }

    override fun decrypt(data: String?): String? {
        throw Exception("Unsupported Cipher")
    }

    override fun decrypt(data: String?, key: String?): String? {
        val cipherKey: Key = SecretKeySpec(stringUtil.nvl(key).toByteArray(), ALGORITHM);
        val cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.DECRYPT_MODE, cipherKey)
        return String(cipher.doFinal(Base64.getDecoder().decode(data)))
    }

}