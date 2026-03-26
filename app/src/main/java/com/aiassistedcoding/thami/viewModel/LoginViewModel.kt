package com.aiassistedcoding.thami.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiassistedcoding.thami.data.UserDao
import kotlinx.coroutines.launch

class LoginViewModel(private val userDao: UserDao) : ViewModel() {

    fun loginUser(
        email: String,
        password: String,
        onResult: (Boolean, String) -> Unit
    ) {
        viewModelScope.launch {
            val user = userDao.getUserByEmail(email)
            if (user != null) {
                if (user.password == password) {
                    onResult(true, "Login successful!")
                } else {
                    onResult(false, "Incorrect password")
                }
            } else {
                onResult(false, "User not found")
            }
        }
    }
}