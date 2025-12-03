package com.mas.lab_semana16_grupo_mas_olortegui.presentation.task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.mas.lab_semana16_grupo_mas_olortegui.data.model.TaskDto
import com.mas.lab_semana16_grupo_mas_olortegui.data.remote.auth.FirebaseAuthDataSource
import com.mas.lab_semana16_grupo_mas_olortegui.data.remote.firestore.TaskRemoteDataSource
import com.mas.lab_semana16_grupo_mas_olortegui.data.repository.AuthRepository
import com.mas.lab_semana16_grupo_mas_olortegui.data.repository.TaskRepository
import kotlinx.coroutines.launch

data class TaskFormState(
    val id: String = "",
    val title: String = "",
    val priority: String = "",
    val deadline: Timestamp? = null,
    val completed: Boolean = false,
    val error: String? = null,
    val isSaving: Boolean = false
)

class TaskFormViewModel(
    private val repository: TaskRepository = TaskRepository(
        TaskRemoteDataSource(),
        AuthRepository(FirebaseAuthDataSource())
    )
) : ViewModel() {

    var uiState by mutableStateOf(TaskFormState())
        private set

    fun loadTask(task: TaskDto) {
        uiState = uiState.copy(
            id = task.id,
            title = task.title,
            priority = task.priority,
            deadline = task.deadline,
            completed = task.completed,
            error = null
        )
    }

    fun onTitleChange(value: String) {
        uiState = uiState.copy(title = value)
    }

    fun onPriorityChange(value: String) {
        uiState = uiState.copy(priority = value)
    }

    fun onDeadlineChange(timestamp: Timestamp?) {
        uiState = uiState.copy(deadline = timestamp)
    }

    fun onCompletedChange(value: Boolean) {
        uiState = uiState.copy(completed = value)
    }

    fun saveTask(onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            uiState = uiState.copy(isSaving = true, error = null)

            val task = TaskDto(
                id = uiState.id,
                title = uiState.title,
                priority = uiState.priority,
                deadline = uiState.deadline,
                completed = uiState.completed
            )

            val result = if (uiState.id.isBlank()) {
                repository.addTask(task)
            } else {
                repository.updateTask(task)
            }

            uiState = if (result.isSuccess) {
                uiState.copy(isSaving = false, error = null)
            } else {
                uiState.copy(
                    isSaving = false,
                    error = result.exceptionOrNull()?.message ?: "Error al guardar"
                )
            }

            onResult(result.isSuccess)
        }
    }
}
