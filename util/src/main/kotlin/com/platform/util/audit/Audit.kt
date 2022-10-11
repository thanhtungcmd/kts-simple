package com.tungbt.util.audit


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Audit(
    val name: String = ""
)
