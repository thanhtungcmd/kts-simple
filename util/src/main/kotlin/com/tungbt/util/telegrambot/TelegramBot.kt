package com.tungbt.util.telegrambot

interface TelegramBot {

    fun getMe(): String?

    fun sendMessage(chatId: String, text: String): String?

    fun getUpdates(): String?

}