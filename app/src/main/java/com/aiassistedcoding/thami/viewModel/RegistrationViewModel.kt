package com.aiassistedcoding.thami.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiassistedcoding.thami.data.User
import com.aiassistedcoding.thami.data.UserDao
import kotlinx.coroutines.launch

class RegistrationViewModel(private val userDao: UserDao) : ViewModel() {
    fun registerUser(
        firstName: String,
        lastName: String,
        email: String,
        phoneNumber: String,
        password: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            val user = User(
                firstName = firstName,
                lastName = lastName,
                email = email,
                phoneNumber = phoneNumber,
                password = password
            )
            userDao.insertUser(user)
            onSuccess()
        }
    }
}