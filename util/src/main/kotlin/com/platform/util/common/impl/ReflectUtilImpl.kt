package com.tungbt.util.common.impl

import com.tungbt.util.common.CollectionUtil
import com.tungbt.util.common.ReflectUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.LocalVariableTableParameterNameDiscoverer
import org.springframework.core.ParameterNameDiscoverer
import org.springframework.stereotype.Component
import java.lang.reflect.Field
import java.lang.reflect.Method

@Component
class ReflectUtilImpl : ReflectUtil {
    private val parameterNameDiscoverer: ParameterNameDiscoverer = LocalVariableTableParameterNameDiscoverer()

    @Autowired
    lateinit var collectionUtil: CollectionUtil

    override fun getStack(): String? {
        return getStack(Throwable())
    }

    fun getStack(e: Throwable?): String? {
        var e = e
        if (e == null) {
            e = Throwable()
        }
        val es = e.stackTrace ?: return null
        val n = es.size
        if (n <= 1) {
            return null
        }
        val se = es[1]
        return se.className + "." + se.methodName + ":" + se.lineNumber
    }

    override fun getParameterNames(method: Method): List<String>? {
        return parameterNameDiscoverer.getParameterNames(method)?.toList()
    }

    override fun getMethods(clazz: Class<*>?): List<Method?>? {
        return if (clazz == null) collectionUtil.emptyList<Method>() else collectionUtil.toList(*clazz.declaredMethods)
    }

    override fun getFields(clazz: Class<*>?): List<Field?>? {
        return if (clazz == null) collectionUtil.emptyList<Field>() else collectionUtil.toList(*clazz.declaredFields)
    }

}