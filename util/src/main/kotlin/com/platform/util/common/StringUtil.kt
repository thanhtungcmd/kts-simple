package com.platform.util.common

interface StringUtil {

    fun <V> parse(v: V?): String

    fun isNull(s:String?): Boolean

    fun isEmpty(s: String?): Boolean

    fun nvl(s: String?): String

    fun trimAll(s: String?): String

    fun upper(s: String?): String

    fun lower(s: String?): String

    fun equals(a: String?, b: String?): Boolean

    fun equalsIgnoreCase(a: String?, b: String?): Boolean

    fun formatIsdn(isdn: String): String

    fun toMsisdn(isdn: String): String

}