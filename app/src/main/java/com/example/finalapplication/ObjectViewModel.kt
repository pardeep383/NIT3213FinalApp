package com.example.finalapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ObjectViewModel(private val repository: ObjectRepository) : ViewModel() {

    private val _loginResult = MutableStateFlow<Result<String>?>(null)
    val loginResult: StateFlow<Result<String>?> = _loginResult

    private val _entities = MutableStateFlow<List<Map<String, Any?>>>(emptyList())
    val entities: StateFlow<List<Map<String, Any?>>> = _entities

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.login(username, password)
                if (response.isSuccessful && response.body() != null) {
                    _loginResult.value = Result.success(response.body()!!.keypass)
                } else {
                    _loginResult.value = Result.failure(Exception("Login failed: ${response.code()}"))
                }
            } catch (e: Exception) {
                _loginResult.value = Result.failure(e)
            }
        }
    }

    fun fetchDashboard(keypass: String, username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.fetchDashboardData(keypass, username, password)
                if (response.isSuccessful && response.body() != null) {
                    _entities.value = response.body()!!.entities
                }
            } catch (e: Exception) {
                _entities.value = emptyList()
            }
        }
    }
}
