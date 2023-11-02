package com.github.gunin_igor75.flow_project.game_score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class GameScoreViewModel : ViewModel() {
//    private var cashState: State = State.Game(0, 0)
//    private var _state = MutableSharedFlow<State>(replay = 1)
//    var state = _state.asSharedFlow()
//        .onEach { cashState = it }

    private var _state = MutableStateFlow<State>(State.Game(0, 0))
    val state = _state.asStateFlow()


    fun increaseScore(team: Team) {
        val currentState = state.value
        if (currentState is State.Game) {
            if (team == Team.TEAM_1) {
                val oldValue = currentState.score1
                val newValue = oldValue + 1
                if (newValue >= WINNER) {
                    _state.value = State.Winner(team, newValue, currentState.score2)
                } else {
                    _state.value = currentState.copy(score1 = newValue)
                }
            } else {
                val oldValue = currentState.score2
                val newValue = oldValue + 1
                if (newValue >= WINNER) {
                    _state.value = State.Winner(team, currentState.score1, newValue)
                } else {
                    _state.value = currentState.copy(score2 = newValue)
                }
            }
        }
    }

    companion object {
        private const val WINNER = 7
    }
}