package com.aiassistedcoding.thami.viewModel

import androidx.lifecycle.ViewModel
import com.aiassistedcoding.thami.data.User
import com.aiassistedcoding.thami.data.UserDao
import kotlinx.coroutines.flow.Flow

class UserListViewModel(private val userDao: UserDao) : ViewModel() {
    val allUsers: Flow<List<User>> = userDao.getAllUsers()
}