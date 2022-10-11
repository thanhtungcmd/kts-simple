package com.platform.app

import com.tungbt.app.util.GenerateUtil
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AuthorApplication

fun main(args: Array<String>) {
	val applicationContext = runApplication<AuthorApplication>(*args)
	val generateUtil: GenerateUtil = applicationContext.getBean(GenerateUtil::class.java)
	generateUtil.generateDummyUsers()
}
