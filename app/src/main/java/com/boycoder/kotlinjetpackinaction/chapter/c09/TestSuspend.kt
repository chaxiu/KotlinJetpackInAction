package com.boycoder.kotlinjetpackinaction.chapter.c09

import kotlinx.coroutines.delay

suspend fun getUserInfo(): String {
    delay(1000L)
    return "zhutao"
}

suspend fun getFriendList(user: String): String {
    delay(1000L)
    return "Tom, Jack"
}

suspend fun getFeedList(list: String): String {
    delay(1000L)
    return "FeedList"
}

suspend fun testCoroutine() {
    cLog("start")
    val user = getUserInfo()
    val friendList = getFriendList(user)
    val feedList = getFeedList(friendList)
    cLog(feedList)
}

suspend fun main() {
    testCoroutine()
}

fun cLog(msg: Any) {
    println("${Thread.currentThread().name} msg=$msg")
}