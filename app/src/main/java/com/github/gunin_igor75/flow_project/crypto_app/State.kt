package com.github.gunin_igor75.flow_project.crypto_app

sealed class State{
    object Initial: State()
    object Loading: State()
    data class Content(val currencyList: List<Currency>): State()
}
