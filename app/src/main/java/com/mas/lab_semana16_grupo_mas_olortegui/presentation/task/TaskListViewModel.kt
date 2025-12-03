package com.mas.lab_semana16_grupo_mas_olortegui.presentation.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mas.lab_semana16_grupo_mas_olortegui.data.model.TaskDto
import com.mas.lab_semana16_grupo_mas_olortegui.data.remote.auth.FirebaseAuthDataSource
import com.mas.lab_semana16_grupo_mas_olortegui.data.remote.firestore.TaskRemoteDataSource
import com.mas.lab_semana16_grupo_mas_olortegui.data.repository.AuthRepository
import com.mas.lab_semana16_grupo_mas_olortegui.data.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class TaskListUiState(
    val isLoading: Boolean = true,
    val tasks: List<TaskDto> = emptyList(),
    val error: String? = null
)

class TaskListViewModel(
    private val repository: TaskRepository = TaskRepository(
        TaskRemoteDataSource(),
        AuthRepository(FirebaseAuthDataSource())
    )
) : ViewModel() {

    private val _uiState = MutableStateFlow(TaskListUiState())
    val uiState: StateFlow<TaskListUiState> = _uiState.asStateFlow()

    init {
        observeTasks()
    }

    private fun observeTasks() {
        viewModelScope.launch {
            repository.getTasksRealtime()
                .collect { tasks ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        tasks = tasks,
                        error = null
                    )
                }
        }
    }

    fun onDeleteTask(taskId: String) {
        viewModelScope.launch {
            repository.deleteTask(taskId)
        }
    }

    fun onToggleCompleted(task: TaskDto) {
        viewModelScope.launch {
            repository.updateTask(
                task.copy(completed = !task.completed)
            )
        }
    }
}
