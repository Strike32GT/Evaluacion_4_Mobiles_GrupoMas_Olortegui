package com.mas.lab_semana16_grupo_mas_olortegui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TaskCard(
    task: TaskUi,
    onClick: () -> Unit = {},
    onToggleComplete: (Boolean) -> Unit = {}
) {
    val priorityColor = when (task.priority) {
        "Alta" -> Color(0xFFE74C3C)
        "Media" -> Color(0xFFF39C12)
        "Baja" -> Color(0xFF27AE60)
        else -> Color.Gray
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {

        Column(
            modifier = Modifier.padding(14.dp)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                Checkbox(
                    checked = task.isCompleted,
                    onCheckedChange = { onToggleComplete(it) }
                )

                Column(modifier = Modifier.weight(1f).padding(start = 4.dp)) {

                    Text(task.title, fontWeight = FontWeight.Bold)

                    Spacer(Modifier.height(4.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .background(priorityColor.copy(alpha = 0.12f), RoundedCornerShape(50))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(task.priority, color = priorityColor)
                        }

                        Spacer(Modifier.width(8.dp))

                        Text(task.status, color = Color.Gray)
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            Text("Fecha: ${task.dueDate}", color = Color.Gray)
        }
    }
}
