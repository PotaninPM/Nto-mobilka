package ru.myitschool.work.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ru.myitschool.work.core.UserData

interface AuthApi {

    @GET("/api/{login}/auth")
    suspend fun authenticate(@Path("login") login: String): Response<Unit>
}
