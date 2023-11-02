package com.github.gunin_igor75.flow_project.game_score

sealed class State{
    data class Game(
        val score1: Int,
        val score2: Int
    ): State()
    data class Winner(
        val winnerTeam: Team,
        val score1: Int,
        val score2: Int
    ): State()
}
