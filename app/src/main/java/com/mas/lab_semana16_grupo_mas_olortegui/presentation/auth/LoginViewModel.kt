package com.mas.lab_semana16_grupo_mas_olortegui.presentation.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mas.lab_semana16_grupo_mas_olortegui.data.remote.auth.FirebaseAuthDataSource
import com.mas.lab_semana16_grupo_mas_olortegui.data.repository.AuthRepository
import kotlinx.coroutines.launch

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

class LoginViewModel(
    private val repository: AuthRepository = AuthRepository(FirebaseAuthDataSource())
) : ViewModel() {

    var uiState by mutableStateOf(LoginUiState())
        private set

    fun onEmailChange(value: String) {
        uiState = uiState.copy(email = value)
    }

    fun onPasswordChange(value: String) {
        uiState = uiState.copy(password = value)
    }

    fun login(onResult: (Boolean) -> Unit) {
        val email = uiState.email.trim()
        val password = uiState.password.trim()

        if (email.isBlank() || password.isBlank()) {
            uiState = uiState.copy(error = "Completa todos los campos")
            onResult(false)
            return
        }

        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null)

            val result = repository.login(email, password)

            uiState = uiState.copy(isLoading = false)

            if (result.isSuccess) {
                onResult(true)
            } else {
                uiState = uiState.copy(
                    error = result.exceptionOrNull()?.message ?: "Error al iniciar sesión"
                )
                onResult(false)
            }
        }
    }
}
