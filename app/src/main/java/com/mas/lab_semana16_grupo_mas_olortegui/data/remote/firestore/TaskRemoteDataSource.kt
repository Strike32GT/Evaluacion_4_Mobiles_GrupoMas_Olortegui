package com.mas.lab_semana16_grupo_mas_olortegui.data.remote.firestore

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.mas.lab_semana16_grupo_mas_olortegui.data.model.TaskDto
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class TaskRemoteDataSource(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {

    private fun userTasks(userId: String) =
        firestore.collection("users")
            .document(userId)
            .collection("tasks")

    // ----------------------- ADD TASK -----------------------
    suspend fun addTask(userId: String, task: TaskDto): Result<Unit> {
        return try {
            val docRef = userTasks(userId).document()
            docRef.set(
                task.copy(id = docRef.id),
                SetOptions.merge()
            ).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // ----------------------- UPDATE TASK -----------------------
    suspend fun updateTask(userId: String, task: TaskDto): Result<Unit> {
        return try {
            userTasks(userId)
                .document(task.id)
                .set(task, SetOptions.merge())
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // ----------------------- DELETE TASK -----------------------
    suspend fun deleteTask(userId: String, taskId: String): Result<Unit> {
        return try {
            userTasks(userId)
                .document(taskId)
                .delete()
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // ----------------------- GET REALTIME -----------------------
    fun getTasksRealtime(userId: String): Flow<List<TaskDto>> = callbackFlow {
        val listener = userTasks(userId)
            .addSnapshotListener { snapshot, error ->

                if (error != null) {
                    trySend(emptyList())
                    return@addSnapshotListener
                }

                val tasks = snapshot?.documents?.mapNotNull { doc ->
                    doc.toObject(TaskDto::class.java)
                } ?: emptyList()

                trySend(tasks)
            }

        awaitClose { listener.remove() }
    }
}
