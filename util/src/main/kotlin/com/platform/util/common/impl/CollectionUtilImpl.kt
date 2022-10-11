package com.platform.util.common.impl

import com.platform.util.common.CollectionUtil
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors
import java.util.stream.Stream

@Component
class CollectionUtilImpl : CollectionUtil {

    override fun <E> emptyList(): List<E>? {
        return Collections.emptyList()
    }

    override fun <E> emptySet(): Set<E>? {
        return Collections.emptySet()
    }

    override fun <E> isNull(c: Collection<E>?): Boolean {
        return c == null
    }

    override fun <E> isNull(vararg a: E): Boolean {
        return a == null
    }

    override fun <E> isEmpty(c: Collection<E>?): Boolean {
        return c == null || c.isEmpty()
    }

    override fun <E> isEmpty(vararg a: E): Boolean {
        return a == null || a.size == 0
    }

    override fun <E> toList(c: Collection<E>): List<E> {
        return if (isNull(c)) Collections.emptyList() else c.stream().filter { e: E? -> e != null }
            .collect(Collectors.toList())
    }

    override fun <E> toList(vararg a: E): List<E> {
        return if (isNull(*a)) Collections.emptyList() else Arrays.stream(a).filter { e -> e != null }
            .collect(Collectors.toList())
    }

    override fun <E> toSet(c: Collection<E>): Set<E>? {
        return if (isNull(c)) Collections.emptySet() else c.stream().filter { e: E? -> e != null }
            .collect(Collectors.toSet())
    }

    override fun <E> toSet(vararg a: E): Set<E>? {
        return if (isNull(*a)) Collections.emptySet() else Arrays.stream(a).filter { e -> e != null }
            .collect(Collectors.toSet())
    }

    override operator fun <E> get(c: Collection<E>): Optional<E>? {
        return toList(c).stream().findFirst()
    }

    override operator fun <E> get(vararg a: E): Optional<E>? {
        return get(toList(*a))
    }

    override fun <E> limit(c: Collection<E>, limit: Long): List<E>? {
        return toList(c).stream().limit(limit).collect(Collectors.toList())
    }

    override fun <E> limit(limit: Long, vararg a: E): List<E>? {
        return limit(toList(*a), limit)
    }

    override fun <E> limit(c: Collection<E>, limit: Long, offset: Long): List<E>? {
        return toList(c).stream().skip(offset).limit(limit).collect(Collectors.toList())
    }

    override fun <E> limit(limit: Long, offset: Long, vararg a: E): List<E>? {
        return limit(toList(*a), limit, offset)
    }

    override fun <E> distinct(c: Collection<E>): List<E> {
        return toList(c).stream().distinct().collect(Collectors.toList())
    }

    override fun <E> distinct(a: Array<E>): List<E> {
        return toList(*a).stream().distinct().collect(Collectors.toList())
    }

    override fun <E> minus(a: Collection<E>, b: Collection<E>): List<E>? {
        val bb = distinct(b)
        return distinct(a).stream().filter { e: E -> !bb.contains(e) }.collect(Collectors.toList())
    }

    override fun <E> equals(a: Collection<E>, b: Collection<E>): Boolean {
        return isEmpty(minus(a, b)) && isEmpty(minus(b, a))
    }

    override fun <E> plus(a: Collection<E>, b: Collection<E>): MutableList<Any?>? {
        return Stream.of(toList(a), toList(b)).flatMap { obj: Collection<*> -> obj.stream() }.distinct()
            .collect(Collectors.toList())
    }

    override operator fun <E> plus(cc: Collection<Collection<E>>): List<E>? {
        return toList(cc).stream().flatMap { obj: Collection<E> -> obj.stream() }.distinct()
            .collect(Collectors.toList())
    }

    override fun <E> contains(v: E?, c: Collection<E>): Boolean {
        return if (isEmpty(c)) false else v == null && c.stream().anyMatch { e: E? -> e == null } || c.stream()
            .anyMatch { e: E -> v == e }
    }

    override fun <E> contains(v: E, vararg a: E): Boolean {
        return contains(v, toList(*a))
    }

}