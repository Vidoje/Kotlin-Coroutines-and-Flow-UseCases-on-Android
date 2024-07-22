package com.lukaslechner.coroutineusecasesonandroid.playground.fundamentals

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("main starts")

    joinAll(
        async { coroutineThreadInfo(1, 500) },
        async { coroutineThreadInfo(2, 300) }
    )

    println("main finishes")
}

suspend fun coroutineThreadInfo(number:Int, delay: Long){
    println("Routine $number starts work ${Thread.currentThread().name}")
    delay(delay)
    println("Routine $number finished ${Thread.currentThread().name}")
}