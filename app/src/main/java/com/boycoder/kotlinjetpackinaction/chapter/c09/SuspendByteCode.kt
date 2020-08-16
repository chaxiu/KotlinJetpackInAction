package com.boycoder.kotlinjetpackinaction.chapter.c09

import kotlin.coroutines.Continuation

fun testCoroutine(completion: Continuation<Any?>): Any? {

    class GetUserInfoContinuation(completion: Continuation<Any?>?) : ContinuationImpl(completion) {
        var label: Int = 0
        var result: Any? = null
        var mUser: Any? = null
        var mFriendList: Any? = null

        override fun resumeWith(_result: Result<Any?>) {
            result = _result
            label = label or Int.Companion.MIN_VALUE
            testCoroutine(this)
        }
    }

    lateinit var continuation: Any
    if (completion is GetUserInfoContinuation) {
        // do some check
        continuation = completion
    } else {
        // init continuation
        continuation = GetUserInfoContinuation(completion)

        lateinit var user: String
        lateinit var friendList: String
        lateinit var feedList: String

        val result = (continuation as GetUserInfoContinuation).result
        var suspendReturn: Any? = null
        val suspendFlag = CoroutineSingletons.COROUTINE_SUSPENDED

        when (continuation.label) {
            0 -> {
                log("start")
                continuation.label = 1
                suspendReturn = getUserInfo(continuation)
                if (suspendReturn == suspendFlag) {
                    return suspendReturn
                }
            }

            1 -> {
                user = result as String
                log(user)
                continuation.mUser = user

                continuation.label = 2
                suspendReturn = getFriendList(user, continuation)
                if (suspendReturn == suspendFlag) {
                    return suspendReturn
                }
            }

            2 -> {
                user = continuation.mUser as String
                friendList = result as String
                log(friendList)

                continuation.mUser = user
                continuation.mFriendList = friendList
                continuation.label = 3

                suspendReturn = getFeedList(friendList, continuation)
                if (suspendReturn == suspendFlag) {
                    return suspendReturn
                }
            }

            3 -> {
                user = continuation.mUser as String
                friendList = continuation.mFriendList as String
                feedList = continuation.result as String
                log(feedList)
            }
        }
    }

    return Unit
}

fun getUserInfo(completion: Continuation<Any?>){

}
fun getFriendList(user: String, completion: Continuation<Any?>){

}
fun getFeedList(friendList: String, completion: Continuation<Any?>){

}
