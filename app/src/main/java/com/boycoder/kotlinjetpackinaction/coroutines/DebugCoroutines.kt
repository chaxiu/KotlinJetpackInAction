package com.boycoder.kotlinjetpackinaction.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking<Unit> {
        fun log(msg: Any) {
            println("${Thread.currentThread().name} msg=$msg")
        }

        log(1)

        launch {
            val a = 4
            delay(300)
            log(a)
        }
        launch {
            val b = 3
            delay(200)
            log(b)
        }
        launch {
            val c = 2
            delay(100)
            log(c)
        }
    }
}

