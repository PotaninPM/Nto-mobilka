package ru.myitschool.work.api

import retrofit2.http.GET
import retrofit2.http.Path
import ru.myitschool.work.core.UserData

interface UserApi {
    @GET("/api/{login}/info")
    suspend fun getUserInfo(@Path("login") login: String): UserData
}