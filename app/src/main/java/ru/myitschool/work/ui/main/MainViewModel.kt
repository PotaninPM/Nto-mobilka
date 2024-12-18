package ru.myitschool.work.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.myitschool.work.core.MainState
import ru.myitschool.work.core.UserData
import ru.myitschool.work.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    val state: StateFlow<MainState> = _state.asStateFlow()

    fun refreshData() {
        viewModelScope.launch {
            _state.value = MainState.Loading
            try {
                val userData = repository.getUserInfo()
                _state.value = MainState.Success(userData)
            } catch (e: Exception) {
                _state.value = MainState.Error("Ошибка загрузки данных. Попробуйте снова.")
            }
        }
    }

    fun logout() {
        repository.clearUserData()
    }
}