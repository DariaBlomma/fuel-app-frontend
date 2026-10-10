package ru.fuelapp.app.ui.auth

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.fuelapp.app.ui.main.MainStubScreen
import ru.fuelapp.app.ui.onboarding.OnboardingScreen

@Composable
fun AuthNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            AuthLoginScreen(
                onLoginSuccess = {
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onForgotPassword = { navController.navigate("forgot") },
                onRegister = { navController.navigate("register") }
            )
        }

        composable("register") {
            AuthRegisterScreen(
                onLogin = { navController.popBackStack() },
                onSuccess = {
                    navController.navigate("onboarding") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("forgot") {
            AuthForgotScreen(
                onLogin = { navController.popBackStack() },
                onCodeSent = { email ->
                    navController.navigate("reset/${Uri.encode(email)}")
                }
            )
        }

        composable("reset/{email}") { entry ->
            val email = entry.arguments?.getString("email").orEmpty()
            val resetViewModel: AuthResetViewModel = viewModel(
                key = email,
                factory = viewModelFactory {
                    initializer { AuthResetViewModel(email) }
                }
            )
            AuthResetScreen(
                email = email,
                viewModel = resetViewModel,
                onSuccess = { navController.popBackStack("login", inclusive = false) }
            )
        }

        composable("onboarding") {
            OnboardingScreen(
                onFinish = {
                    navController.navigate("main") {
                        popUpTo("onboarding") { inclusive = true }
                    }
                }
            )
        }

        composable("main") {
            MainStubScreen()
        }
    }
}