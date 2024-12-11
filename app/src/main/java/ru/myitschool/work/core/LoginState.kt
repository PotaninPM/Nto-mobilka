package ru.myitschool.work.core

data class LoginState(
    val success: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)
