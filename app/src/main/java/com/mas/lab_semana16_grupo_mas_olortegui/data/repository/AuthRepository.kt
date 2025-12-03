package com.mas.lab_semana16_grupo_mas_olortegui.data.repository

import com.mas.lab_semana16_grupo_mas_olortegui.data.remote.auth.FirebaseAuthDataSource

class AuthRepository(
    private val remote: FirebaseAuthDataSource
) {

    fun getCurrentUserId(): String? = remote.getCurrentUserId()

    suspend fun register(email: String, password: String) =
        remote.register(email, password)

    suspend fun login(email: String, password: String) =
        remote.login(email, password)

    fun logout() = remote.logout()
}
