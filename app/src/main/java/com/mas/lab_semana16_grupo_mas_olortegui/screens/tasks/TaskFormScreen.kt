package com.mas.lab_semana16_grupo_mas_olortegui.screens.tasks

import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.sin

@Composable
fun TaskFormScreen() {
    var titulo by remember { mutableStateOf("") }
    var prioridad by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "", //Aqui va si quieres editar o agregar tarea
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ){
        innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF5F6FA))
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ){
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ){

                    OutlinedTextField(
                        value = titulo,
                        onValueChange = {titulo == it},
                        label = {Text(text = "Titulo de la tarea")},
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    ExposedDropdownMenuBox(
                        expanded = false,
                        onExpandedChange = {}
                    ) {
                        OutlinedTextField(
                            value = prioridad,
                            onValueChange = {prioridad == it},
                            readOnly = true,
                            label = {Text("Prioridad")},
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth()
                        )

                    }

                    OutlinedTextField(
                        value = dueDate,
                        onValueChange = {dueDate==it},
                        label = {Text(text = "Fecha Limite ejemplo (2025-12-10)")},
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    Text(
                        text = "Estado",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ){
                        status.forEach { item ->
                            FilterChip(
                                selected = status == item,
                                onClick = {
                                    status = item
                                },
                                label = {
                                    Text(text = item)
                                }
                            )
                        }
                    }

                    Button(
                        onClick = {
                            onSaveClick(titulo, prioridad, dueDate, status)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(16.dp)
                    ){
                        Text(text = "")
                    }


                }
            }
        }
    }
}