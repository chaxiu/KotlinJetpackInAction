package com.boycoder.kotlinjetpackinaction.chapter.c09

import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext

abstract class ContinuationImpl(
        completion: Continuation<Any?>?,
        private val _context: CoroutineContext?
) : Continuation<Any?> {
    constructor(completion: Continuation<Any?>?) : this(completion, completion?.context)

    public override val context: CoroutineContext
        get() = _context!!

    @Transient
    private var intercepted: Continuation<Any?>? = null

    public fun intercepted(): Continuation<Any?> =
            intercepted
                    ?: (context[ContinuationInterceptor]?.interceptContinuation(this) ?: this)
                            .also { intercepted = it }
}

enum class CoroutineSingletons { COROUTINE_SUSPENDED, UNDECIDED, RESUMED }
