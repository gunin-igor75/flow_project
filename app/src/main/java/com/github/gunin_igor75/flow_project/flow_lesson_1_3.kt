package com.github.gunin_igor75.flow_project

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

suspend fun main() {
    val flows = getFlowCollectionBuilder()
    flows
        .filter { it.isPrime() }
        .map { "Number $it" }
        .collect { println(it) }

    val flow = getFlowCollectionBuilder()
    val list = flow.map { "Number $it" }
        .toList()
    println(list.joinToString())
    val flowCount = getFlowCollectionBuilder()
    val count = flowCount.map { "Number $it" }.count()
    val flowFirst = getFlowCollectionBuilder()
    val first = flowFirst.map { "Number $it" }.first()
    val flowLast = getFlowCollectionBuilder()
    val last = flowLast.map { "Number $it" }.last()
}

private fun getFlowBuilder(): Flow<Int> {
    return flow {
        val numbers = listOf(1, 2, 4, 5, 7, 13, 4, 333, 44, 41)
        numbers.forEach {
            delay(10)
            emit(it)
        }
    }
}


private fun getFlowAs() = listOf(1, 2, 4, 5, 7, 13, 4, 333, 44, 41).asFlow()

private fun getFlowOfBuilder(): Flow<Int> {
    return flowOf(1, 2, 4, 5, 7, 13, 4, 333, 44, 41)
}

private fun getFlowCollectionBuilder(): Flow<Int> {
    val flows = getFlowBuilder()
    return flow {
        emitAll(flows)
    }
}

private suspend fun Int.isPrime(): Boolean {
    if (this <= 1) return false
    for (i in 2..this / 2) {
        delay(100)
        if (this % i == 0) {
            return false
        }
    }
    return true
}