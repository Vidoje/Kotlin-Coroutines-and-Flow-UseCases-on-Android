package com.lukaslechner.coroutineusecasesonandroid.playground.fundamentals

fun main(){
    println("Main starts")
    routine(1, 500)
    routine(2, 300)
    println("Main finishes")
}

fun routine(number:Int, delay: Long){
    println("Routine $number starts work")
    Thread.sleep(delay)
    println("Routine $number finished")
}