package com.mas.lab_semana16_grupo_mas_olortegui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mas.lab_semana16_grupo_mas_olortegui.screens.auth.LoginScreen
import com.mas.lab_semana16_grupo_mas_olortegui.screens.auth.RegisterScreen
import com.mas.lab_semana16_grupo_mas_olortegui.screens.tasks.TaskFormScreen
import com.mas.lab_semana16_grupo_mas_olortegui.screens.tasks.TaskListScreen

object Routes {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val TASK_LIST = "task_list"
    const val TASK_FORM = "task_form"
    const val TASK_FORM_WITH_ID = "task_form/{id}"
}

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN,
        modifier = modifier
    ) {

        // LOGIN
        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Routes.TASK_LIST) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                },
                onGoToRegister = { navController.navigate(Routes.REGISTER) }
            )
        }

        // REGISTER
        composable(Routes.REGISTER) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.REGISTER) { inclusive = true }
                    }
                },
                onGoToLogin = { navController.popBackStack() }
            )
        }


        composable(Routes.TASK_LIST) {
            TaskListScreen(
                onAddTask = { navController.navigate(Routes.TASK_FORM) },
                onEditTask = { id -> navController.navigate("task_form/$id") }
            )
        }

        // CREAR TAREA
        composable(Routes.TASK_FORM) {
            TaskFormScreen(
                onBack = { navController.popBackStack() },
                onSaveSuccess = { navController.popBackStack() }
            )
        }


        composable(
            route = Routes.TASK_FORM_WITH_ID,
            arguments = listOf(navArgument("id") { defaultValue = "" })
        ) { entry ->

            val id = entry.arguments?.getString("id") ?: ""

            TaskFormScreen(
                taskId = id,
                onBack = { navController.popBackStack() },
                onSaveSuccess = { navController.popBackStack() }
            )
        }
    }
}
