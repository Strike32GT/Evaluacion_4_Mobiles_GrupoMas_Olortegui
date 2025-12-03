package com.mas.lab_semana16_grupo_mas_olortegui.screens.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.Timestamp
import com.mas.lab_semana16_grupo_mas_olortegui.presentation.task.TaskFormViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskFormScreen(
    taskId: String? = null,
    onBack: () -> Unit = {},
    onSaveSuccess: () -> Unit = {}
)
 {
    val viewModel: TaskFormViewModel = viewModel()
    val ui = viewModel.uiState

    val prioridades = listOf("Alta", "Media", "Baja")
    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nueva Tarea", fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF5F6FA))
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {

                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    // ------------- TÍTULO -------------
                    OutlinedTextField(
                        value = ui.title,
                        onValueChange = viewModel::onTitleChange,
                        label = { Text("Título de la tarea") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    // ------------- PRIORIDAD -------------
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        OutlinedTextField(
                            value = ui.priority,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Prioridad") },
                            modifier = Modifier
                                .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                                .fillMaxWidth()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            prioridades.forEach { p ->
                                DropdownMenuItem(
                                    text = { Text(p) },
                                    onClick = {
                                        viewModel.onPriorityChange(p)
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }

                    // ------------- FECHA -------------
                    OutlinedTextField(
                        value = ui.deadline?.toDate()?.toString()?.substring(0,10) ?: "",
                        onValueChange = { dateStr ->
                            val parts = dateStr.split("-")
                            if (parts.size == 3) {
                                try {
                                    val cal = Calendar.getInstance()
                                    cal.set(parts[0].toInt(), parts[1].toInt() - 1, parts[2].toInt(), 0, 0, 0)
                                    cal.set(Calendar.MILLISECOND, 0)
                                    viewModel.onDeadlineChange(Timestamp(cal.time))
                                } catch (_: Exception) {}
                            }
                        },
                        label = { Text("Fecha Límite (2025-12-10)") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    // ------------- ESTADO -------------
                    Text("Estado", fontWeight = FontWeight.SemiBold)

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        val estados = listOf("Pendiente", "Completado")

                        estados.forEach { estado ->
                            FilterChip(
                                selected = (ui.completed && estado == "Completado"),
                                onClick = { viewModel.onCompletedChange(estado == "Completado") },
                                label = { Text(estado) }
                            )
                        }
                    }

                    // ------------- GUARDAR -------------
                    Button(
                        onClick = {
                            viewModel.saveTask { success ->
                                if (success) onSaveSuccess()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        if (ui.isSaving) {
                            CircularProgressIndicator(
                                color = Color.White,
                                strokeWidth = 2.dp,
                                modifier = Modifier.size(22.dp)
                            )
                        } else {
                            Text("Guardar")
                        }
                    }
                }
            }
        }
    }
}
