package com.github.gunin_igor75.flow_project.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UsersViewModel: ViewModel() {

    private val repository = UserRepository

    private var _users = MutableLiveData<List<String>>()
    val users: LiveData<List<String>>
        get() = _users

    init {
        loadUsers()
    }

    fun addUser(user: String) {
        viewModelScope.launch {
            UserRepository.addUser(user)
        }
    }

    private fun loadUsers() {
        viewModelScope.launch {
            UserRepository.loadUsers()
                .collect{
                    _users.value = it
                }
        }
    }
}