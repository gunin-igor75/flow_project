package com.github.gunin_igor75.flow_project

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

val coroutineScope = CoroutineScope(Dispatchers.IO)

suspend fun main() {
    val flow = getFlow()
    val job = coroutineScope.launch {
        flow.collect()
        flow.collect()
    }
    job.join()
}


fun getFlow() = flow {
    repeat(10){
        println("Emitted $it")
        emit(it)
        delay(1000)
    }
}