package ru.myitschool.work.repository

import android.content.SharedPreferences
import ru.myitschool.work.api.UserApi
import ru.myitschool.work.core.UserData
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserApi,
    private val preferences: SharedPreferences
) {

    suspend fun getUserInfo(): UserData {
        return api.getUserInfo(login = preferences.getString("login", "") ?: "")
    }

    fun clearUserData() {
        preferences.edit().clear().apply()
    }
}