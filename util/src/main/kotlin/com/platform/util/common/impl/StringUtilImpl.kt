package com.platform.util.common.impl

import com.platform.util.common.StringUtil
import org.springframework.stereotype.Component

@Component
class StringUtilImpl: StringUtil {

    override fun <V> parse(v: V?): String {
        if (v == null) {
            return ""
        }
        return if (v is String) {
            nvl(v as String?)
        } else nvl(v.toString())
    }

    override fun isNull(s:String?): Boolean {
        return s.isNullOrBlank();
    }

    override fun isEmpty(s: String?): Boolean {
        return s.isNullOrBlank()
    }

    override fun nvl(s: String?): String {
        if (s.isNullOrBlank()) {
            return ""
        }
        return s.trim()
    }

    fun trim(s: String?): String {
        return nvl(s)
    }

    override fun trimAll(s: String?): String {
        return trim(nvl(s).replace("\\s+".toRegex(), " "))
    }

    override fun upper(s: String?): String {
        return nvl(s).uppercase();
    }

    override fun lower(s: String?): String {
        return nvl(s).lowercase()
    }

    override fun equals(a: String?, b: String?): Boolean {
        return nvl(a) == nvl(b)
    }

    override fun equalsIgnoreCase(a: String?, b: String?): Boolean {
        return nvl(a).equals(nvl(b), ignoreCase = true)
    }

    override fun formatIsdn(isdn: String): String {
        if (isEmpty(isdn)) {
            return isdn
        }
        if (isdn.length < 9) {
            return isdn
        }
        var result = isdn
        if (result.length > 9 && (isdn.startsWith("+") || result.startsWith("0"))) {
            result = result.substring(1)
        }
        val countryPrefix = "84"
        if (result.length > 9 && result.substring(0, countryPrefix.length) == countryPrefix) result =
            result.substring(countryPrefix.length)
        return "0$result"
    }

    override fun toMsisdn(isdn: String): String {
        var isdn = isdn
        isdn = nvl(isdn)
        if (isEmpty(isdn)) {
            return isdn
        }
        while (isdn.startsWith("+") || isdn.startsWith("0")) {
            isdn = isdn.substring(1)
        }
        val countryPrefix = "84"
        if (isdn.length <= 9 || isdn.substring(0, countryPrefix.length) != countryPrefix) {
            isdn = "84$isdn"
        }
        return isdn
    }

}