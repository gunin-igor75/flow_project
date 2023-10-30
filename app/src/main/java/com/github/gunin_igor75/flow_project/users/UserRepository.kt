package com.github.gunin_igor75.flow_project.users

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object UserRepository {

    private var users = mutableListOf("Nick", "Jon", "Max")

    suspend fun addUser(user: String) {
        delay(10)
        users.add(user)
    }

    suspend fun loadUsers(): Flow<List<String>> = flow {
        while (true) {
            emit(users.toList())
            delay(500)
        }
    }
}