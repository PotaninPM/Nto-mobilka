package ru.myitschool.work.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.myitschool.work.core.LoginState
import ru.myitschool.work.repository.AuthRepository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    fun login(username: String) {
        viewModelScope.launch {
//            _state.value = LoginState(success = true)
            _state.value = LoginState(isLoading = true)
            try {
                val response = repository.authenticate(username)
                if (response.isSuccessful) {
                    _state.value = LoginState(success = true)
                } else {
                    _state.value = LoginState(error = "Ошибка авторизации")
                }
            } catch (e: Exception) {
                _state.value = LoginState(success = true)
                //_state.value = LoginState(error = e.message)
            }
        }
    }
}
