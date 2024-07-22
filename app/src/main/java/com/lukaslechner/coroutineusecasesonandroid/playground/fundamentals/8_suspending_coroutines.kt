package com.lukaslechner.coroutineusecasesonandroid.playground.fundamentals

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("main starts")

    joinAll(
        async { suspendingCoroutines(1, 500) },
        async { suspendingCoroutines(2, 300) },

        async {
            repeat(5) {
                println("Another task is working ${Thread.currentThread().name}")
                delay(100)
            }

        }
    )

    println("main finishes")
}

suspend fun suspendingCoroutines(number: Int, delay: Long) {
    println("Routine $number starts work ${Thread.currentThread().name}")
    delay(delay)
    println("Routine $number finished ${Thread.currentThread().name}")
}