package com.platform.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.platform")
class SimpleApplication

fun main(args: Array<String>) {
	runApplication<SimpleApplication>(*args)
}
