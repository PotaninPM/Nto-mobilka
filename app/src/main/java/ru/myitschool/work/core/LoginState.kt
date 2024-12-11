package ru.myitschool.work.core

data class LoginState(
    val isLoading: Boolean = false,
    val error: String? = null
)
