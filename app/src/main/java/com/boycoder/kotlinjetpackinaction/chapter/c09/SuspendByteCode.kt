package com.boycoder.kotlinjetpackinaction.chapter.c09

import kotlin.coroutines.Continuation

fun testCoroutine(completion: Continuation<Any?>): Any? {

    class TestContinuation(completion: Continuation<Any?>?) : ContinuationImpl(completion) {
        // 表示协程状态机当前的状态
        var label: Int = 0
        // 协程返回结果
        var result: Any? = null

        // 用于保存之前协程的计算结果
        var mUser: Any? = null
        var mFriendList: Any? = null

        // invokeSuspend 是协程的关键
        // 它最终会调用 testCoroutine(this) 开启协程状态机
        // 状态机相关代码就是后面的 when 语句
        // 协程的本质，可以说就是 CPS + 状态机
        override fun invokeSuspend(_result: Result<Any?>): Any? {
            result = _result
            label = label or Int.Companion.MIN_VALUE
            return testCoroutine(this)
        }
    }

    val continuation = if (completion is TestContinuation) {
        completion
    } else {
        TestContinuation(completion)
    }

    // 三个变量，对应原函数的三个变量
    lateinit var user: String
    lateinit var friendList: String
    lateinit var feedList: String

    // result 接收协程的运行结果
    var result = continuation.result

    // suspendReturn 接收挂起函数的返回值
    var suspendReturn: Any? = null

    // CoroutineSingletons 是个枚举类
    // COROUTINE_SUSPENDED 代表当前函数被挂起了
    val sFlag = CoroutineSingletons.COROUTINE_SUSPENDED

    var loop = true

    while (loop) {
        // 协程状态机核心代码
        when (continuation.label) {
            0 -> {
                // 检测异常
                throwOnFailure(result)

                log("start")
                // 将 label 置为 1，准备进入下一次状态
                continuation.label = 1

                // 执行 getUserInfo
                suspendReturn = getUserInfo(continuation)

                // 判断是否挂起
                if (suspendReturn == sFlag) {
                    return suspendReturn
                } else {
                    result = suspendReturn
                    //go to next state
                }
            }

            1 -> {
                throwOnFailure(result)

                // 获取 user 值
                user = result as String
                log(user)
                // 将协程结果存到 continuation 里
                continuation.mUser = user
                // 准备进入下一个状态
                continuation.label = 2

                // 执行 getFriendList
                suspendReturn = getFriendList(user, continuation)

                // 判断是否挂起
                if (suspendReturn == sFlag) {
                    return suspendReturn
                } else {
                    result = suspendReturn
                    //go to next state
                }
            }

            2 -> {
                throwOnFailure(result)

                user = continuation.mUser as String

                // 获取 friendList 的值
                friendList = result as String
                log(friendList)

                // 将协程结果存到 continuation 里
                continuation.mUser = user
                continuation.mFriendList = friendList

                // 准备进入下一个状态
                continuation.label = 3

                // 执行 getFeedList
                suspendReturn = getFeedList(friendList, continuation)

                // 判断是否挂起
                if (suspendReturn == sFlag) {
                    return suspendReturn
                } else {
                    result = suspendReturn
                    //go to next state
                }
            }

            3 -> {
                throwOnFailure(result)

                user = continuation.mUser as String
                friendList = continuation.mFriendList as String
                feedList = continuation.result as String
                log(feedList)
                loop = false
            }
        }
    }


    return Unit
}

private fun throwOnFailure(value: Any?){
    if (value is Result<*>) {
        value.exceptionOrNull()
    }
}

fun getUserInfo(completion: Continuation<Any?>): Any?{
    // no implement
    return CoroutineSingletons.COROUTINE_SUSPENDED
}
fun getFriendList(user: String, completion: Continuation<Any?>): Any?{
    return CoroutineSingletons.COROUTINE_SUSPENDED
}
fun getFeedList(friendList: String, completion: Continuation<Any?>): Any?{
    return CoroutineSingletons.COROUTINE_SUSPENDED
}

suspend fun noSuspendFriendList(): String{
    return "Tom, Jack"
}
