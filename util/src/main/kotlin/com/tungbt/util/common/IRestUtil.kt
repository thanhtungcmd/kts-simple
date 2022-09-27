package com.tungbt.util.common

interface IRestUtil {

    fun callGet(url:String, headers: Map<String, String>, params: Map<String, String>): String

    fun callPost(url:String, headers: Map<String, String>, body: Map<String, String>): String

}