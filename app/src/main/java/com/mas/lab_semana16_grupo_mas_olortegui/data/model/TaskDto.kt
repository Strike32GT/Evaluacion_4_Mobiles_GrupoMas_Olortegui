package com.mas.lab_semana16_grupo_mas_olortegui.data.model

import com.google.firebase.Timestamp

data class TaskDto(
    val id: String = "",
    val title: String = "",
    val priority: String = "",
    val deadline: Timestamp? = null,
    val completed: Boolean = false,
    val userId: String = ""
)
