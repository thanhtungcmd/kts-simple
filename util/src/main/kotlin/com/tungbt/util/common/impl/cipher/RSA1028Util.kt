package com.tungbt.util.common.impl.cipher

import com.tungbt.util.common.CipherUtil
import org.springframework.stereotype.Component
import java.security.KeyFactory
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*
import javax.crypto.Cipher

@Component
class RSA1028Util: CipherUtil {

    private val ALGORITHM = "RSA"

    override fun encrypt(value: String?): String? {
        throw Exception("Unsupported Cipher")
    }

    override fun encrypt(value: String?, key: String?): String? {
        val spec = X509EncodedKeySpec(key?.toByteArray())
        val factory: KeyFactory = KeyFactory.getInstance(ALGORITHM)
        val pubKey: PublicKey = factory.generatePublic(spec)
        val c: Cipher = Cipher.getInstance(ALGORITHM)
        c.init(Cipher.ENCRYPT_MODE, pubKey)
        val encryptOut: ByteArray = c.doFinal(value?.toByteArray())
        return Base64.getEncoder().encodeToString(encryptOut)
    }

    override fun decrypt(data: String?): String? {
        throw Exception("Unsupported Cipher")
    }

    override fun decrypt(data: String?, key: String?): String {
        val spec = PKCS8EncodedKeySpec(key?.toByteArray())
        val factory = KeyFactory.getInstance("RSA")
        val priKey = factory.generatePrivate(spec)
        val c = Cipher.getInstance("RSA")
        c.init(Cipher.DECRYPT_MODE, priKey)
        return String(c.doFinal(Base64.getDecoder().decode(data)))
    }

}