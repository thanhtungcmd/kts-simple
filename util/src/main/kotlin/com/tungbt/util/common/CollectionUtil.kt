package com.tungbt.util.common

import java.util.*

interface CollectionUtil {

    fun <E> emptyList(): List<E>?

    fun <E> emptySet(): Set<E>?

    fun <E> isNull(c: Collection<E>?): Boolean

    fun <E> isNull(vararg a: E): Boolean

    fun <E> isEmpty(c: Collection<E>?): Boolean

    fun <E> isEmpty(vararg a: E): Boolean

    fun <E> toList(c: Collection<E>): List<E>

    fun <E> toList(vararg a: E): List<E>

    fun <E> toSet(c: Collection<E>): Set<E>?

    fun <E> toSet(vararg a: E): Set<E>?

    fun <E> get(c: Collection<E>): Optional<E>?

    fun <E> get(vararg a: E): Optional<E>?

    fun <E> limit(c: Collection<E>, limit: Long): List<E>?

    fun <E> limit(limit: Long, vararg a: E): List<E>?

    fun <E> limit(c: Collection<E>, limit: Long, offset: Long): List<E>?

    fun <E> limit(limit: Long, offset: Long, vararg a: E): List<E>?

    fun <E> distinct(c: Collection<E>): List<E>

    fun <E> distinct(a: Array<E>): List<E>

    fun <E> minus(a: Collection<E>, b: Collection<E>): List<E>?

    fun <E> equals(a: Collection<E>, b: Collection<E>): Boolean

    fun <E> plus(a: Collection<E>, b: Collection<E>): MutableList<Any?>?

    fun <E> plus(cc: Collection<Collection<E>>): List<E>?

    fun <E> contains(v: E?, c: Collection<E>): Boolean

    fun <E> contains(v: E, vararg a: E): Boolean

}