package com.mas.lab_semana16_grupo_mas_olortegui.screens.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mas.lab_semana16_grupo_mas_olortegui.components.TaskCard
import com.mas.lab_semana16_grupo_mas_olortegui.components.TaskUi
import com.mas.lab_semana16_grupo_mas_olortegui.presentation.task.TaskListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    onAddTask: () -> Unit = {},
    onEditTask: (String) -> Unit = {}
) {
    val vm: TaskListViewModel = viewModel()
    val ui = vm.uiState.collectAsState().value

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Mis tareas") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddTask) {
                Icon(Icons.Default.Add, contentDescription = "Agregar tarea")
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF5F6FA))
                .padding(8.dp)
        ) {

            ui.tasks.forEach { task ->
                TaskCard(
                    task = TaskUi.fromDto(task),
                    onClick = { onEditTask(task.id) },
                    onToggleComplete = { checked ->
                        vm.onToggleCompleted(task.copy(completed = checked))
                    }
                )
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}
