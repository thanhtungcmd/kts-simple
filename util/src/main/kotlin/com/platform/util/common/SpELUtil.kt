package com.platform.util.common

import java.lang.reflect.Method

interface SpELUtil {

    fun catchArgs(keys: Array<String>, method: Method, args: Array<Any>): List<Any?>?

}