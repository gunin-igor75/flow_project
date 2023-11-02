package com.github.gunin_igor75.flow_project.users

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

suspend fun main() {
    val coroutineScope = CoroutineScope(Dispatchers.Default)
    val flow = MutableSharedFlow<Int>()


    val producer = coroutineScope.launch {
        repeat(10){
            println("Emitted $it")
            flow.emit(it)
            println("After emit: $it")
            delay(200)
        }
    }

    val consumer = coroutineScope.launch {
        flow.collect{
            println("Collected consumer $it")
            delay(5000)
        }
    }

    val consumerNew = coroutineScope.launch {
        flow.collect{
//            delay(1000)
            println("Collected consumerNew $it")
        }
    }

    producer.join()
    consumer.join()
    consumerNew.join()
}