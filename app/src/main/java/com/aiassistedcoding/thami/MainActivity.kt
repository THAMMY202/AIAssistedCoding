package com.aiassistedcoding.thami

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.aiassistedcoding.thami.data.AppDatabase
import com.aiassistedcoding.thami.ui.theme.AIAssistedCodingTheme
import com.aiassistedcoding.thami.viewModel.LoginViewModel
import com.aiassistedcoding.thami.viewModel.RegistrationViewModel
import com.aiassistedcoding.thami.viewModel.UserListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val database = remember { AppDatabase.getDatabase(context) }
            val registrationViewModel = remember { RegistrationViewModel(database.userDao()) }
            val loginViewModel = remember { LoginViewModel(database.userDao()) }
            val userListViewModel = remember { UserListViewModel(database.userDao()) }

            var currentScreen by remember { mutableStateOf("login") }

            AIAssistedCodingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    when (currentScreen) {
                        "login" -> {
                            LoginScreen(
                                viewModel = loginViewModel,
                                onLoginSuccess = { currentScreen = "userList" },
                                onSignUpClick = { currentScreen = "registration" },
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        "registration" -> {
                            RegistrationScreen(
                                viewModel = registrationViewModel,
                                onLoginClick = { currentScreen = "login" },
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        "userList" -> {
                            UserListScreen(
                                viewModel = userListViewModel,
                                onBackClick = { currentScreen = "login" },
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}
