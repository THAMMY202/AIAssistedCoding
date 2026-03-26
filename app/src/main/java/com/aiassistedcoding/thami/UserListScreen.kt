package com.aiassistedcoding.thami

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aiassistedcoding.thami.data.User
import com.aiassistedcoding.thami.ui.theme.AIAssistedCodingTheme
import com.aiassistedcoding.thami.viewModel.UserListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    viewModel: UserListViewModel,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val users by viewModel.allUsers.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registered Users") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        UserListContent(
            users = users,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun UserListContent(
    users: List<User>,
    modifier: Modifier = Modifier
) {
    if (users.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            Text("No users registered yet.")
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(users) { user ->
                UserItem(user = user)
            }
        }
    }
}

@Composable
fun UserItem(user: User) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "${user.firstName} ${user.lastName}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = user.email,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserListScreenPreview() {
    val sampleUsers = listOf(
        User(id = 1, firstName = "John", lastName = "Doe", email = "john.doe@example.com", phoneNumber = "1234567890", password = "password"),
        User(id = 3, firstName = "Thammy", lastName = "Shabalala", email = "thammy202@gmail.com", phoneNumber = "1234567890", password = "password"),
        User(id = 4, firstName = "Nomtha", lastName = "Shabalala", email = "notha202@gmail.com", phoneNumber = "1234567890", password = "password"),
        User(id = 5, firstName = "John", lastName = "Doe", email = "john.doe@example.com", phoneNumber = "1234567890", password = "password"),
        User(id = 6, firstName = "John", lastName = "Doe", email = "john.doe@example.com", phoneNumber = "1234567890", password = "password"),
        User(id = 2, firstName = "Jane", lastName = "Smith", email = "jane.smith@example.com", phoneNumber = "0987654321", password = "password")
    )
    AIAssistedCodingTheme {
        Scaffold(
            topBar = {
                @OptIn(ExperimentalMaterial3Api::class)
                TopAppBar(title = { Text("Registered Users") })
            }
        ) { padding ->
            UserListContent(users = sampleUsers, modifier = Modifier.padding(padding))
        }
    }
}
