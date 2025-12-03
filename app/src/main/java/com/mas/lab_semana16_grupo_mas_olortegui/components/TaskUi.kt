package com.mas.lab_semana16_grupo_mas_olortegui.components

import com.mas.lab_semana16_grupo_mas_olortegui.data.model.TaskDto

data class TaskUi(
    val id: String,
    val title: String,
    val priority: String,
    val dueDate: String,
    val status: String,
    val isCompleted: Boolean
) {
    companion object {
        fun fromDto(dto: TaskDto): TaskUi {
            return TaskUi(
                id = dto.id,
                title = dto.title,
                priority = dto.priority,
                dueDate = dto.deadline?.toDate()?.toString()?.substring(0,10) ?: "",
                status = if (dto.completed) "Completado" else "Pendiente",
                isCompleted = dto.completed
            )
        }
    }
}
