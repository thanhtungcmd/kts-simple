package com.tungbt.author

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.tungbt")
class SimpleApplication

fun main(args: Array<String>) {
	runApplication<SimpleApplication>(*args)
}
