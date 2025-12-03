package com.mas.lab_semana16_grupo_mas_olortegui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.mas.lab_semana16_grupo_mas_olortegui.navigation.AppNavGraph
import com.mas.lab_semana16_grupo_mas_olortegui.ui.theme.Lab_Semana16_Grupo_Mas_OlorteguiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            Lab_Semana16_Grupo_Mas_OlorteguiTheme {
                val navController = rememberNavController()

                AppNavGraph(
                    navController = navController,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
