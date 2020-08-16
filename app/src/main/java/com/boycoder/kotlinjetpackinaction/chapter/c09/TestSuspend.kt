package com.boycoder.kotlinjetpackinaction.chapter.c09

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

suspend fun getUserInfo(): String {
    withContext(Dispatchers.IO) {
        delay(1000L)
    }
    return "BoyCoder"
}

//挂起函数
// ↓
suspend fun getFriendList(user: String): String {
    withContext(Dispatchers.IO) {
        delay(1000L)
    }
    return "Tom, Jack"
}

//挂起函数
// ↓
suspend fun getFeedList(list: String): String {
    withContext(Dispatchers.IO) {
        delay(1000L)
    }
    return "{FeedList..}"
}

suspend fun testCoroutine() {
    log("start")
    val user = getUserInfo()
    log(user)
    val friendList = getFriendList(user)
    log(friendList)
    val feedList = getFeedList(friendList)
    log(feedList)
}

suspend fun main() {
    testCoroutine()
}

fun log(msg: Any) {
    println("${Thread.currentThread().name} msg=$msg")
}