package com.github.gunin_igor75.flow_project.crypto_app

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlin.random.Random


object CryptoRepository {

    private val currencies = listOf("BTH", "ETH", "USDT", "BNB", "USDC")
    private val currencyList = mutableListOf<Currency>();
    private val _currencyListFlow = MutableSharedFlow<List<Currency>>()
    val currencyListFlow = _currencyListFlow.asSharedFlow()
    private val refreshEvents = MutableSharedFlow<Unit>()
    private val coroutineScope = CoroutineScope(Dispatchers.Default)


    val getCurrencyListFlow: Flow<List<Currency>> = flow {
        delay(3000)
        generateCurrencyList()
        emit(currencyList.toList())
        refreshEvents.collect {
            delay(3000)
            generateCurrencyList()
            emit(currencyList.toList())
        }
    }.stateIn(
        coroutineScope,
        started = SharingStarted.Lazily,
        initialValue = currencyList.toList()
    )

    suspend fun refreshList() {
        refreshEvents.emit(Unit)
    }

    private fun generateCurrencyList() {
        val prices = buildList {
            repeat(currencies.size) {
                add(Random.nextInt(1000, 2000))
            }
        }
        val data = buildList {
            for ((index, currencyName) in currencies.withIndex()) {
                val price = prices[index]
                val currency = Currency(currencyName, price)
                add(currency)
            }
        }
        currencyList.clear()
        currencyList.addAll(data)
    }

    //    suspend fun loadData() {
//        delay(3000)
//        generateCurrencyList()
//        _currencyListFlow.emit(currencyList.toList())
//    }

    //    fun getCurrencyList(): Flow<List<Currency>> = flow {
//        emit(currencyList.toList())
//        while (true) {
//            delay(3000)
//            generateCurrencyList()
//            emit(currencyList.toList())
//        }
//    }
}