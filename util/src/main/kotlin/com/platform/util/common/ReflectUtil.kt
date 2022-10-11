package com.platform.util.common

import java.lang.reflect.Field
import java.lang.reflect.Method

interface ReflectUtil {

    fun getStack(): String?

    fun getParameterNames(method: Method): List<String>?

    fun getMethods(clazz: Class<*>?): List<Method?>?

    fun getFields(clazz: Class<*>?): List<Field?>?

}