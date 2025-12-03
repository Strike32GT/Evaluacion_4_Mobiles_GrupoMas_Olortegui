package com.mas.lab_semana16_grupo_mas_olortegui.data.remote.firestore

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.mas.lab_semana16_grupo_mas_olortegui.data.model.TaskDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TaskRemoteDataSource(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {

    private fun userTasks(userId: String) =
        firestore.collection("users")
            .document(userId)
            .collection("tasks")

    suspend fun addTask(userId: String, task: TaskDto): Result<Unit> {
        return Result.success(Unit)
    }

    fun getTasksRealtime(userId: String): Flow<List<TaskDto>> {
        return flow { emit(emptyList<TaskDto>()) }
    }

    suspend fun updateTask(userId: String, task: TaskDto): Result<Unit> {
        return Result.success(Unit)
    }

    suspend fun deleteTask(userId: String, taskId: String): Result<Unit> {
        return Result.success(Unit)
    }
}
