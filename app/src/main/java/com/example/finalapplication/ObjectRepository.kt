package com.example.finalapplication

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ObjectRepository(private val apiService: ApiService) {

    suspend fun login(username: String, password: String): Response<LoginResponse> {
        return withContext(Dispatchers.IO) {
            val credentials = mapOf(
                "username" to username,
                "password" to password
            )

            Log.d("LOGIN_REQ", "Sending credentials: $credentials")

            apiService.login(credentials)
        }
    }

    suspend fun fetchDashboardData(
        keypass: String,
        username: String,
        password: String
    ): Response<DashboardResponse> {
        return withContext(Dispatchers.IO) {
            apiService.getDashboardData(keypass, username, password)
        }
    }
}
