package com.mas.lab_semana16_grupo_mas_olortegui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mas.lab_semana16_grupo_mas_olortegui.screens.auth.LoginScreen
import com.mas.lab_semana16_grupo_mas_olortegui.screens.auth.RegisterScreen

object Routes {
    const val LOGIN = "login"
    const val REGISTER = "register"
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

        // ---------------- LOGIN ----------------
        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    // Por ahora no navegamos a otro lado
                },
                onGoToRegister = {
                    navController.navigate(Routes.REGISTER)
                }
            )
        }


        composable(Routes.REGISTER) {
            RegisterScreen(onRegisterSuccess = {
                navController.navigate(Routes.LOGIN) {
                    popUpTo(Routes.REGISTER) { inclusive = true }
                }
            }
            )
        }
    }
}