package ru.myitschool.work.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/api/login")
    suspend fun authenticate(@Body username: String): Response<Unit>
}
