package ru.myitschool.work.repository

import android.content.SharedPreferences
import retrofit2.Response
import ru.myitschool.work.api.AuthApi
import ru.myitschool.work.api.UserApi
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthApi,
    private val preferences: SharedPreferences
) {
    suspend fun authenticate(username: String): Response<Unit> {
        return api.authenticate(username)
    }

    fun saveUsername(username: String) {
        preferences.edit().putString("login", username).apply()
    }
}
