package com.mas.lab_semana16_grupo_mas_olortegui.data.repository

import com.mas.lab_semana16_grupo_mas_olortegui.data.model.TaskDto
import com.mas.lab_semana16_grupo_mas_olortegui.data.remote.firestore.TaskRemoteDataSource
import kotlinx.coroutines.flow.Flow

class TaskRepository(
    private val remote: TaskRemoteDataSource,
    private val authRepository: AuthRepository
) {

    private fun requireUserId(): String =
        authRepository.getCurrentUserId()
            ?: throw IllegalStateException("Usuario no logueado")

    fun getTasksRealtime(): Flow<List<TaskDto>> {
        val uid = requireUserId()
        return remote.getTasksRealtime(uid)
    }

    suspend fun addTask(task: TaskDto): Result<Unit> {
        val uid = requireUserId()
        return remote.addTask(uid, task)
    }

    suspend fun updateTask(task: TaskDto): Result<Unit> {
        val uid = requireUserId()
        return remote.updateTask(uid, task)
    }

    suspend fun deleteTask(taskId: String): Result<Unit> {
        val uid = requireUserId()
        return remote.deleteTask(uid, taskId)
    }
}
