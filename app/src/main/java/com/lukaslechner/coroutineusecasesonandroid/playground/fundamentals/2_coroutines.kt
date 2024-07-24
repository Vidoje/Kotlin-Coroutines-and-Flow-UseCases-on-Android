package com.lukaslechner.coroutineusecasesonandroid.playground.fundamentals

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("main starts")

    joinAll(
        async { coroutine(1, 500) },
        async { coroutine(2, 300) }
    )

    println("main finishes")
}

suspend fun coroutine(number:Int, delay: Long){
    println("Routine $number starts work")
    delay(delay)
    println("Routine $number finished")
}