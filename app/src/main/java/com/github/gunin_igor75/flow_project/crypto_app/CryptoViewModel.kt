package com.github.gunin_igor75.flow_project.crypto_app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {

    private val repository = CryptoRepository

    private var _state = MutableLiveData<State>(State.Initial)

    private val loadingFlow = MutableSharedFlow<State>()

    val state = repository.getCurrencyList()
        .filter { it.isNotEmpty() }
        .map { State.Content(it) as State }
        .onStart { emit(State.Loading) }
        .mergeWidth(loadingFlow)


    fun refreshList() {
        viewModelScope.launch {
            loadingFlow.emit(State.Loading)
            repository.refreshList()
        }
    }

    private fun <T> Flow<T>.mergeWidth(another: Flow<T>): Flow<T> {
        return merge(this, another)
    }

    companion object {
        private const val TAG = "CryptoViewModel"
    }

    //    init {
//        viewModelScope.launch {
//            repository.loadData()
//        }
//    }

    //    val state = repository.currencyListFlow
//        .filter { it.isNotEmpty() }
//        .map { State.Content(it) as State }
//        .onStart { emit(State.Loading) }


//    val state: LiveData<State> = repository.getCurrencyList()
//        .filter { it.isNotEmpty() }
//        .map { State.Content(it) as State }
//        .onStart {
//            emit(State.Loading)
//        }
//        .onEach { _state.value = it }
//        .asLiveData()


//    private fun loadData() {
//        val currencyList = repository.getCurrencyList()
//        currencyList
//            .onStart {
//                val currentState = _state.value
//                if (currentState !is State.Content || currentState.currencyList.isEmpty()) {
//                    _state.value = State.Loading
//                }
//            }
//            .filter { it.isNotEmpty() }
//            .onEach {
//                _state.value = State.Content(it)
//            }
//            .launchIn(viewModelScope)
//    }
//
//    private fun loadDataOne() {
//        val currencyList = repository.getCurrencyList()
//        currencyList
//            .filter { it.isNotEmpty() }
//            .map { State.Content(it) as State }
//            .onStart { emit(State.Loading) }
//            .onEach { _state.value = it }
//            .launchIn(viewModelScope)
//    }

}