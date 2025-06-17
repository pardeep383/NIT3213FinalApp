package com.example.finalapplication

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("sydney/auth")
    suspend fun login(
        @Body credentials: Map<String, String>
    ): Response<LoginResponse>

    @GET("dashboard/{keypass}")
    suspend fun getDashboardData(
        @Path("keypass") keypass: String,
        @Query("username") username: String,
        @Query("password") password: String
    ): Response<DashboardResponse>
}
