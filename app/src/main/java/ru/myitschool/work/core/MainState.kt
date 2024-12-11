package ru.myitschool.work.core

sealed class MainState {
    data object Idle : MainState()
    data object Loading : MainState()
    data class Success(val data: UserData) : MainState()
    data class Error(val message: String) : MainState()
}