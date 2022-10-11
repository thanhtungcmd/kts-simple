package com.platform.util.telegrambot.impl

import com.platform.util.common.RestUtil
import com.platform.util.telegrambot.TelegramBot
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class TelegramBotImpl: TelegramBot {

    private val url: String = "https://api.telegram.org/bot"

    @Value("\${platform.telegrambot.token}")
    lateinit var token: String

    @Autowired
    lateinit var restUtil: RestUtil

    override fun getMe(): String? {
        val response: String? = restUtil.callPost("$url$token/getMe")
        return response
    }

    override fun sendMessage(chatId: String, text: String): String? {
        val body: MutableMap<String, String> = mutableMapOf(
            "chat_id" to chatId,
            "text" to text,
            "parse_mode" to "html")
        val response: String? = restUtil.callPost("$url$token/sendMessage", body)
        return response
    }

    override fun getUpdates(): String? {
        val response: String? = restUtil.callPost("$url$token/getUpdates")
        return response
    }

}