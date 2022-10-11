package com.platform.util.common

interface RestUtil {

    fun callGet(url:String, headers: Map<String, String>, params: Map<String, String>): String?

    fun callGet(url:String, headers: Map<String, String>): String?

    fun callGet(url:String): String?

    fun callPost(url:String, headers: Map<String, String>, body: Map<String, String>): String?

    fun callPost(url:String, body: Map<String, String>): String?

    fun callPost(url:String): String?

}