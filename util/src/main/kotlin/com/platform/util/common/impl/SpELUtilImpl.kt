package com.platform.util.common.impl

import com.platform.util.common.CollectionUtil
import com.platform.util.common.ReflectUtil
import com.platform.util.common.SpELUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.expression.Expression
import org.springframework.expression.ExpressionParser
import org.springframework.expression.spel.support.StandardEvaluationContext
import java.lang.reflect.Method

import java.util.stream.Collectors
import org.springframework.expression.spel.standard.SpelExpressionParser
import org.springframework.stereotype.Component

@Component
class SpELUtilImpl : SpELUtil {

    private val expressionParser: ExpressionParser = SpelExpressionParser()

    @Autowired
    lateinit var reflectUtil: ReflectUtil

    @Autowired
    lateinit var collectionUtil: CollectionUtil

    override fun catchArgs(keys: Array<String>, method: Method, args: Array<Any>): List<Any?>? {
        val ks: List<String> = collectionUtil.distinct(keys)
        if (collectionUtil.isEmpty(ks) || collectionUtil.isEmpty(args)) {
            return collectionUtil.emptyList()
        }
        val context = buildContext(method, args)
        return ks.stream().map { key: String ->
            catchValue<Any>(
                key,
                context,
                args
            )
        }.collect(Collectors.toList())
    }

    private fun buildContext(method: Method, args: Array<Any>): StandardEvaluationContext {
        val context = StandardEvaluationContext()
        val parameterNames = reflectUtil.getParameterNames(method)
        if (collectionUtil.isEmpty(parameterNames)) {
            return context
        }
        val size = parameterNames!!.size
        for (i in 0 until size) {
            context.setVariable(parameterNames[i], args[i])
        }
        return context
    }

    private fun <T> catchValue(key: String, context: StandardEvaluationContext, args: Array<Any>): T? {
        return try {
            val expression: Expression = expressionParser.parseExpression(key)
            var value: T? = catchValue(expression, args)
            if (value == null) {
                value = catchValue(expression, context)
            }
            value
        } catch (e: Exception) {
            null
        }
    }

    private fun <T> catchValue(expression: Expression, args: Array<Any>): T? {
        return try {
            expression.getValue(args) as T?
        } catch (e: Exception) {
            null
        }
    }

    private fun <T> catchValue(expression: Expression, context: StandardEvaluationContext): T? {
        return try {
            expression.getValue(context) as T?
        } catch (e: Exception) {
            null
        }
    }

}