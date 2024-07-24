package com.lukaslechner.coroutineusecasesonandroid.playground.coroutinebuilders

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val startTime = System.currentTimeMillis()


    val deffered1 = async {
        val result1 = networkCall(1)
        println("Result: $result1 after ${ellapsedMilis(startTime)} ms")
        result1
    }

    val deffered2 = async {
        val result2 = networkCall(2)
        println("Result: $result2 after ${ellapsedMilis(startTime)} ms")
        result2
    }

    val resultList = listOf(deffered1.await(), deffered2.await())
    println("Result list $resultList")
}

suspend fun networkCall(number: Int): String{
    delay(500)
    return "Result $number"
}

fun ellapsedMilis(startTime: Long) = System.currentTimeMillis() - startTime